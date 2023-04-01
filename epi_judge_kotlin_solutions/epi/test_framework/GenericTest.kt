package epi.test_framework

import epi.test_framework.minimal_json.Json

object GenericTest {
    /**
     * The main test starter. Is intended to be called from [ ][.runFromAnnotations].
     * @param commandlineArgs - command-line options
     * @param testFile        - name of the file we are running
     * @param testDataFile    - name of the file containing header and test data
     * without path prefix
     * @param testFunc        - method to be tested
     * @param comparator      - optional custom result comparator.
     * The 1st argument is expected value,
     * the 2nd is the computed result
     * @param expectedType    - optional custom expected value type if it doesn't
     */
    private fun genericTestMain(commandlineArgs: Array<String>, testFile: String,
                                testDataFile: String, testFunc: Method,
                                comparator: BiPredicate<Object?, Object?>, expectedType: Field,
                                programConfig: Consumer<TestConfig>?): TestResult {
        var configOverride: JsonObject? = null
        configOverride = try {
            Json.parse(String(Files.readAllBytes(Paths.get(
                    TestUtils.getFilePathInJudgeDir("config.json")))))
                    .asObject()
        } catch (e: IOException) {
            throw RuntimeException("config.json file not found")
        }
        return try {
            val config: TestConfig = TestConfig.fromCommandLine(
                    testFile, testDataFile, configOverride.get("timeoutSeconds").asInt(),
                    configOverride.get("numFailedTestsBeforeStop").asInt(),
                    commandlineArgs)
            if (programConfig != null) {
                programConfig.accept(config)
            }
            Platform.setOutputOpts(config.ttyMode, config.colorMode)
            val testHandler = GenericTestHandler(testFunc, comparator, expectedType)
            runTests(testHandler, config)
        } catch (e: RuntimeException) {
            System.err.printf("\nCritical error(%s): %s\n", e.getClass().getName(),
                    e.getMessage())
            e.printStackTrace()
            TestResult.RUNTIME_EXCEPTION
        }
    }

    private fun runTests(handler: GenericTestHandler,
                         config: TestConfig): TestResult {
        val testData: List<List<String>> = TestUtils.splitTsvFile(
                Paths.get(config.testDataDir, config.testDataFile))
        handler.parseSignature(testData[0])
        val metricNames: List<String> = config.metricNamesOverride.apply(handler.metricNames())
        var testNr = 0
        var testsPassed = 0
        val totalTests: Int = testData.size() - 1
        val metrics: List<List<Integer>> = ArrayList()
        val durations: List<Long> = ArrayList()
        var result: TestResult = TestResult.FAILED
        for (testCase in testData.subList(1, testData.size())) {
            testNr++

            // Since the last field of testData is testExplanation, which is not
            // used for running test, we extract that here.
            val testExplanation = testCase[testCase.size() - 1]
            testCase = testCase.subList(0, testCase.size() - 1)
            var testOutput = TestOutput(null, null)
            var testFailure = TestFailure()
            try {
                testOutput = handler.runTest(config.timeoutSeconds,
                        config.metricsOverride, testCase)
                result = TestResult.PASSED
                testsPassed++
                metrics.add(testOutput.metrics)
                durations.add(testOutput.timer.getMicroseconds())
            } catch (e: TestFailure) {
                result = TestResult.FAILED
                testFailure = e
            } catch (e: TimeoutException) {
                result = TestResult.TIMEOUT
                testOutput.timer = e.getTimer()
            } catch (e: StackOverflowError) {
                result = TestResult.STACK_OVERFLOW
            } catch (e: RuntimeException) {
                throw e
            } catch (e: Error) {
                throw e
            } catch (e: Exception) {
                result = TestResult.UNKNOWN_EXCEPTION
                testFailure = TestFailure(e.getClass().getName())
                        .withProperty(TestFailure.PropertyName.EXCEPTION_MESSAGE,
                                e.getMessage())
            }
            TestUtilsConsole.printTestInfo(result, testNr, totalTests,
                    testFailure.getDescription(),
                    testOutput.timer)
            if (result !== TestResult.PASSED) {
                if (!handler.expectedIsVoid()) {
                    testCase = testCase.subList(0, testCase.size() - 1)
                }
                if (!testExplanation.equals("") && !testExplanation.equals("TODO")) {
                    testFailure.withProperty(TestFailure.PropertyName.EXPLANATION,
                            testExplanation)
                }
                TestUtilsConsole.printFailedTest(handler.paramNames(), testCase,
                        testFailure)
                val testsNotPassed = testNr - testsPassed
                if (testsNotPassed >= config.numFailedTestsBeforeStop) {
                    break
                }
            }
        }
        if (config.updateJs) {
            updateTestPassed(config.testFile, testsPassed)
        }
        System.out.println()
        if (!durations.isEmpty()) {
            val complexity = ""
            if (!metricNames.isEmpty() && !metrics.isEmpty() &&
                    config.analyzeComplexity) {
                TestUtilsConsole.showComplexityNotification()
            }
            TestUtilsConsole.printPostRunStats(testsPassed, totalTests, complexity,
                    durations)
        }
        return result
    }

    @SuppressWarnings("unchecked")
    private fun updateTestPassed(testFile: String, testsPassed: Int) {
        var testFile = testFile
        val problemMappingFilePath: Path = Paths.get(TestUtils.getFilePathInJudgeDir("problem_mapping.js"))
        var chapterToProblemToLanguageSolutionMapping: JsonValue? = null
        val JS_BEGIN_PATTERN = "problem_mapping = "
        val JS_END_PATTERN = ";"
        try {
            var jsFileStr = String(Files.readAllBytes(problemMappingFilePath))
            jsFileStr = jsFileStr.replace(JS_BEGIN_PATTERN, "").replace(JS_END_PATTERN, "")
            chapterToProblemToLanguageSolutionMapping = Json.parse(jsFileStr)
        } catch (e: IOException) {
            throw RuntimeException("problem_mapping.js file not found")
        }
        testFile = "Java: $testFile"
        for (chapter in chapterToProblemToLanguageSolutionMapping.asObject()) {
            for (problem in chapter.getValue().asObject()) {
                for (language in problem.getValue().asObject()) {
                    if (testFile.equals(language.getName())) {
                        language.getValue().asObject().set("passed", testsPassed)
                        try {
                            PrintWriter(
                                    problemMappingFilePath.toAbsolutePath().toString()).use { problemMappingFile ->
                                problemMappingFile.print(JS_BEGIN_PATTERN)
                                chapterToProblemToLanguageSolutionMapping.writeTo(
                                        problemMappingFile, WriterConfig.PRETTY_PRINT)
                                problemMappingFile.print(JS_END_PATTERN)
                            }
                        } catch (e: IOException) {
                            throw RuntimeException("problem_mapping.js file not found")
                        }
                        return
                    }
                }
            }
        }
    }

    /**
     * This method prepares arguments for
     * [.genericTestMain] method and consequently invokes it for each * method in
     * the class, marked with [EpiTest] annotation. It scans the * provided
     * class for custom result comparator (marked with [EpiTestComparator]
     * annotation) and for custom expected value type (marked with [ ] annotation)
     */
    @SuppressWarnings("unchecked")
    fun runFromAnnotations(commandlineArgs: Array<String>,
                           testFile: String,
                           testClass: Class): TestResult {
        val comparator: BiPredicate<Object?, Object?> = findCustomComparatorByAnnotation(testClass)
        val expectedType: Field? = findCustomExpectedTypeByAnnotation(testClass)
        val programConfig: Consumer<TestConfig> = findProgramConfigByAnnotation(testClass)
        val testFunc: Method = findMethodWithAnnotation(testClass, EpiTest::class.java)
                ?: throw RuntimeException("Missing method with EpiTest annotation")
        return genericTestMain(commandlineArgs, testFile,
                testFunc.getAnnotation(EpiTest::class.java).testDataFile(),
                testFunc, comparator, expectedType, programConfig)
    }

    private fun findFieldWithAnnotation(testClass: Class,
                                        annotationClass: Class<out Annotation?>): Field? {
        for (f in testClass.getFields()) {
            if (f.getAnnotation(annotationClass) != null) {
                return f
            }
        }
        return null
    }

    private fun findMethodWithAnnotation(testClass: Class,
                                         annotationClass: Class<out Annotation?>): Method? {
        for (m in testClass.getMethods()) {
            if (m.getAnnotation(annotationClass) != null) {
                return m
            }
        }
        return null
    }

    private fun findCustomComparatorByAnnotation(testClass: Class): BiPredicate<Object, Object>? {
        val m: Method = findMethodWithAnnotation(testClass, EpiTestComparator::class.java)
                ?: return null
        return label@ BiPredicate<Object, Object> { expected, result ->
            try {
                return@label m.invoke(null, expected, result)
            } catch (e: IllegalAccessException) {
                throw RuntimeException(e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException(e)
            }
        }
    }

    private fun findProgramConfigByAnnotation(testClass: Class): Consumer<TestConfig>? {
        val m: Method = findMethodWithAnnotation(testClass, EpiProgramConfig::class.java)
                ?: return null
        return Consumer<TestConfig> { config ->
            try {
                m.invoke(null, config)
            } catch (e: IllegalAccessException) {
                throw RuntimeException(e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException(e)
            }
        }
    }

    private fun findCustomExpectedTypeByAnnotation(testClass: Class): Field? {
        return findFieldWithAnnotation(testClass, EpiTestExpectedType::class.java)
    }
}