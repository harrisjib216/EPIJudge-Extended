package epi.test_framework

import epi.TreeLike

/**
 * The central class in generic test runner framework.
 * It is responsible for constructing string deserializers
 * based on the argument types of the tested method (obtained with reflexions)
 * (see [.GenericTestHandler],
 * asserting that the method signature matches the one from the test file header
 * (see [.parseSignature] and
 * executing tests on the provided method (which includes
 * the deserialization of the provided arguments and the expected value,
 * invocation of the target method with these arguments and
 * comparison of the computed result with the expected value)
 * (see [.runTest]).
 *
 *
 * [.parseSignature] and [.runTest]
 * throw [RuntimeException] in case of any error or assertion failure.
 * This exception terminates testing and, consequently, the test program. If
 * tested method throws [TestFailure] or [StackOverflowError], the
 * current test is marked as failed and the execution goes on. In case of any
 * other exception thrown by the tested method, the test program is terminated.
 *
 *
 */
class GenericTestHandler(func: Method, comp: BiPredicate<Object?, Object?>,
                         expectedType: Field?) {
    private val func: Method
    private var paramTypes: List<Type>
    private var hasExecutorHook: Boolean
    private val paramTraits: List<SerializationTrait>
    private val paramNames: List<String>
    private var retValueTrait: SerializationTrait? = null
    private val comp: BiPredicate<Object, Object>?
    private val customExpectedType: Boolean

    /**
     * This constructor initializes type parsers for all arguments and return type
     * of func.
     *
     * @param func         - a method to test.
     * @param comp         - an optional comp for result. If comp is null, values
     * are compared with equals().
     * @param expectedType - can be used with a custom comp that has different
     * types for expected and result arguments.
     */
    init {
        this.func = func
        this.comp = comp
        hasExecutorHook = false
        paramTypes = List.of(func.getGenericParameterTypes())
        if (paramTypes.size() >= 1 &&
                paramTypes[0].equals(TimedExecutor::class.java)) {
            hasExecutorHook = true
            paramTypes = paramTypes.subList(1, paramTypes.size())
        }
        if (paramTypes.size() >= 1 && paramTypes[0].equals(TestTimer::class.java)) {
            throw RuntimeException("This program uses deprecated TestTimer hook")
        }
        paramTraits = paramTypes.stream()
                .map(TraitsFactory::getTrait)
                .collect(Collectors.toList())
        paramNames = Arrays.stream(func.getParameters())
                .map(Parameter::getName)
                .collect(Collectors.toList())
        if (hasExecutorHook) {
            paramNames.remove(0)
        }
        retValueTrait = if (expectedType == null) {
            TraitsFactory.getTrait(func.getGenericReturnType())
        } else {
            TraitsFactory.getTrait(expectedType.getGenericType())
        }
        customExpectedType = expectedType != null
    }

    /**
     * This method ensures that test data header matches with the signature
     * of the method provided in constructor.
     *
     * @param signature - the header from a test data file.
     */
    fun parseSignature(signature: List<String>) {
        if (signature.size() !== paramTypes.size() + 1) {
            throw RuntimeException("Signature parameter count mismatch")
        }
        for (i in 0 until paramTypes.size()) {
            matchTypeNames(paramTraits[i].name(), signature[i],
                    String.format("%d argument", i))
        }
        if (!customExpectedType) {
            matchTypeNames(retValueTrait.name(), signature[signature.size() - 1],
                    "Return value")
        }
    }

    private fun matchTypeNames(expected: String, fromTestData: String,
                               sourceName: String) {
        if (!expected.equals(TestUtils.filterBracketComments(fromTestData))) {
            throw RuntimeException(
                    String.format("%s type mismatch: expected %s, got %s", sourceName,
                            expected, fromTestData))
        }
    }

    /**
     * This method is invoked for each row in a test data file (except the
     * header). It deserializes the list of arguments and calls the user method
     * with them.
     *
     * @param timeoutSeconds - number of seconds to timeout.
     * @param metricsOverride -
     * @param testArgs - serialized arguments.
     * @return array, that contains [result of comparison of expected and result,
     * expected, result]. Two last entries are omitted in case of the void return
     * type
     */
    @Throws(Exception::class, Error::class)
    fun runTest(
            timeoutSeconds: Long,
            metricsOverride: BiFunction<List<Integer?>?, List<Object?>?, List<Integer?>?>,
            testArgs: List<String?>): TestOutput {
        return try {
            val expectedParamCount: Int = paramTraits.size() + if (expectedIsVoid()) 0 else 1
            if (testArgs.size() !== expectedParamCount) {
                throw RuntimeException(
                        String.format("Invalid argument count: expected %d, actual %d",
                                expectedParamCount, testArgs.size()))
            }
            val parsed: List<Object> = ArrayList()
            for (i in 0 until paramTraits.size()) {
                parsed.add(paramTraits[i].parse(Json.parse(testArgs[i])))
            }
            var metrics: List<Integer> = calculateMetrics(parsed)
            metrics = metricsOverride.apply(metrics, parsed)
            val result: Object
            val executor = TimedExecutor(timeoutSeconds)
            result = if (hasExecutorHook) {
                parsed.add(0, executor)
                func.invoke(null, parsed.toArray())
            } else {
                executor.run { func.invoke(null, parsed.toArray()) }
            }
            if (!expectedIsVoid()) {
                val expected: Object = retValueTrait.parse(Json.parse(testArgs[testArgs.size() - 1]))
                assertResultsEqual(expected, result)
            }
            TestOutput(executor.getTimer(), metrics)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e.getMessage())
        } catch (e: InvocationTargetException) {
            val t: Throwable = e.getCause()
            if (t is Exception) {
                throw t as Exception
            } else if (t is Error) {
                throw t as Error
            } else {
                // Improbable except for intended attempts to break the code, but anyway
                throw RuntimeException(t)
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Throws(TestFailure::class)
    private fun assertResultsEqual(expected: Object, result: Object) {
        val comparisonResult: Boolean
        comparisonResult = if (comp != null) {
            comp.test(expected, result)
        } else if (expected == null) {
            result == null
        } else if (expected is Float && result is Float) {
            TestUtils.floatComparison(expected as Float, result as Float)
        } else if (expected is Double && result is Double) {
            TestUtils.doubleComparison(expected as Double, result as Double)
        } else if (expected is TreeLike<*, *> && result is TreeLike<*, *>) {
            BinaryTreeUtils.assertEqualBinaryTrees(expected as TreeLike<Object, *>,
                    result as TreeLike<Object, *>)
            return
        } else {
            expected.equals(result)
        }
        if (!comparisonResult) {
            throw TestFailure()
                    .withProperty(TestFailure.PropertyName.EXPECTED, expected)
                    .withProperty(TestFailure.PropertyName.RESULT, result)
        }
    }

    fun metricNames(): List<String> {
        return IntStream.range(0, Math.min(paramTraits.size(), paramNames.size()))
                .mapToObj { i -> paramTraits[i].getMetricNames(paramNames[i]) }
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
    }

    private fun calculateMetrics(params: List<Object>): List<Integer> {
        return IntStream.range(0, Math.min(paramTraits.size(), params.size()))
                .mapToObj { i -> paramTraits[i].getMetrics(params[i]) }
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
    }

    fun expectedIsVoid(): Boolean {
        return retValueTrait.isVoid()
    }

    fun paramNames(): List<String> {
        return paramNames
    }
}