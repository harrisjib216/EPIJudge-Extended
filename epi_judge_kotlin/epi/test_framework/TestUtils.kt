package epi.test_framework

import java.io.IOException

object TestUtils {
    fun splitTsvFile(tsvFile: Path?): List<List<String>> {
        val FIELD_DELIM = "\t"
        var inputData: Stream<String?>? = null
        inputData = try {
            Files.lines(tsvFile)
        } catch (e: IOException) {
            throw RuntimeException("Test data file not found")
        }
        val asList: List<String> = inputData.collect({ ArrayList() }, ArrayList::add, ArrayList::addAll)
        val result: List<List<String>> = ArrayList()
        for (line in asList) {
            result.add(List.of(line.split(FIELD_DELIM)))
        }
        return result
    }

    val defaultTestDataDirPath: String
        get() {
            val MAX_SEARCH_DEPTH = 4
            val DIR_NAME = "test_data"
            var path: Path = Paths.get(".").toAbsolutePath()
            for (i in 0 until MAX_SEARCH_DEPTH) {
                if (Files.isDirectory(path.resolve(DIR_NAME))) {
                    return path.resolve(DIR_NAME).toString()
                }
                path = path.getParent()
                if (path == null) {
                    break
                }
            }
            throw RuntimeException(
                    "Can't find test data directory. Please start the program with \"--test_data_dir <path>\" command-line option")
        }

    fun getFilePathInJudgeDir(fileName: String?): String {
        return Paths.get(defaultTestDataDirPath)
                .getParent()
                .resolve(fileName)
                .toAbsolutePath()
                .toString()
    }

    /**
     * Serialized type name can contain multiple comments, enclosed into brackets.
     * This method removes all such comments.
     */
    fun filterBracketComments(s: String): String {
        val BRACKET_ENCLOSED_COMMENT = "(\\[[^\\]]*\\])"
        return s.replaceAll(BRACKET_ENCLOSED_COMMENT, "").replaceAll(" ", "")
    }

    /**
     * Check that result list has the same count of elements as reference.
     * TestFailure is thrown in case of mismatch.
     */
    @Throws(TestFailure::class)
    fun <T> assertAllValuesPresent(reference: List<T>,
                                   result: List<T>) {
        val referenceSet: Map<T, Integer> = HashMap()
        for (x in reference) {
            referenceSet.put(x, referenceSet.getOrDefault(x, 0) + 1)
        }
        for (x in result) {
            referenceSet.put(x, referenceSet.getOrDefault(x, 0) - 1)
        }
        val excessItems: List<T> = ArrayList()
        val missingItems: List<T> = ArrayList()
        referenceSet.forEach { x, count ->
            if (count < 0) {
                while (count++ < 0) {
                    excessItems.add(x)
                }
            } else if (count > 0) {
                while (count-- > 0) {
                    missingItems.add(x)
                }
            }
        }
        if (!excessItems.isEmpty() || !missingItems.isEmpty()) {
            val e: TestFailure = TestFailure("Value set changed")
                    .withProperty(TestFailure.PropertyName.RESULT, result)
            if (!excessItems.isEmpty()) {
                e.withProperty(TestFailure.PropertyName.EXCESS_ITEMS, excessItems)
            }
            if (!missingItems.isEmpty()) {
                e.withProperty(TestFailure.PropertyName.MISSING_ITEMS, missingItems)
            }
            throw e
        }
    }

    /**
     * Non-exact float comparison
     */
    fun floatComparison(f1: Float, f2: Float): Boolean {
        val eps = 1E-4f
        val absEps = 1E-10f
        return Math.abs(f1 - f2) <=
                Math.max(eps * Math.max(Math.abs(f1), Math.abs(f2)), absEps)
    }

    /**
     * Non-exact double comparison
     */
    fun doubleComparison(d1: Double, d2: Double): Boolean {
        val eps = 1E-6
        val absEps = 1E-20
        return Math.abs(d1 - d2) <=
                Math.max(eps * Math.max(Math.abs(d1), Math.abs(d2)), absEps)
    }
}