package epi.test_framework

import java.nio.file.Files

/**
 * This class contains parameters that control test execution
 */
class TestConfig(
        /**
         * Program source filename
         */
        var testFile: String,
        /**
         * Name of corresponding .tsv file
         */
        var testDataFile: String, timeoutSeconds: Long,
        numFailedTestsBeforeStop: Int) {
    /**
     * Path to directory with .tsv files
     */
    var testDataDir: String? = null

    /**
     * If TRUE, enable advanced output (mainly usage of \r)
     * If INDETERMINATE, try to autodetect if output is console
     */
    var ttyMode: TriBool

    /**
     * If TRUE, enable colored output
     * If INDETERMINATE, try to autodetect if output is console
     */
    var colorMode: TriBool

    /**
     * If True, update problem_mapping.js
     */
    var updateJs: Boolean

    /**
     * If > 0, run each test with a timeout
     */
    var timeoutSeconds: Long

    /**
     * Number of failures, before the testing is terminated
     * If zero, testing is never terminated
     */
    var numFailedTestsBeforeStop: Int

    /**
     * If True, enable solution complexity analyze
     */
    var analyzeComplexity: Boolean

    /**
     * If > 0, calculate complexity with timeout
     */
    var complexityTimeout: Long

    /**
     * Function for adjusting list of metric names
     * By default identity function
     * Another function may be set in programConfig callback
     */
    var metricNamesOverride: Function<List<String>, List<String>>

    /**
     * Function for adjusting list of generated metrics
     * All changes should be isomorphic to metricNamesOverride
     * By default returns identical metrics list
     * Another function may be set in programConfig callback
     */
    var metricsOverride: BiFunction<List<Integer>, List<Object>, List<Integer>>

    init {
        ttyMode = TriBool.INDETERMINATE
        colorMode = TriBool.INDETERMINATE
        updateJs = true
        this.timeoutSeconds = timeoutSeconds
        this.numFailedTestsBeforeStop = numFailedTestsBeforeStop
        analyzeComplexity = false
        complexityTimeout = 20
        metricNamesOverride = Function<List<String>, List<String>> { names -> names }
        metricsOverride = BiFunction<List<Integer>, List<Object>, List<Integer>> { metrics, funcArgs -> metrics }
    }

    companion object {
        private fun getParam(commandlineArgs: Array<String>, i: Int,
                             argName: String): String {
            if (i >= commandlineArgs.size) {
                throw RuntimeException("CL: Missing parameter for $argName")
            }
            return commandlineArgs[i]
        }

        private fun printUsageAndExit() {
            val usageString = """usage: <program name> [-h] [--test-data-dir [TEST_DATA_DIR]]
                    [--force-tty] [--no-tty] [--force-color] [--no-color]

optional arguments:
  -h, --help                         show this help message and exit
  --test-data-dir [TEST_DATA_DIR]    path to test_data directory
  --force-tty                        enable tty features (like printing output on the same line) even in case stdout is not a tty device
  --no-tty                           never use tty features
  --force-color                      enable colored output even in case stdout is not a tty device
  --no-color                         never use colored output
  --no-update-js                     no update problem_mapping.js
"""
            System.out.print(usageString)
            System.exit(0)
        }

        fun fromCommandLine(testFile: String, testDataFile: String,
                            timeoutSeconds: Long,
                            numFailedTestsBeforeStop: Int,
                            commandlineArgs: Array<String>): TestConfig {
            // Set numFailedTestsBeforeStop to 0, means users want to run as many as
            // tests in one run.
            var numFailedTestsBeforeStop = numFailedTestsBeforeStop
            if (numFailedTestsBeforeStop == 0) {
                numFailedTestsBeforeStop = Integer.MAX_VALUE
            }
            val config = TestConfig(testFile, testDataFile, timeoutSeconds,
                    numFailedTestsBeforeStop)
            var i = 0
            while (i < commandlineArgs.size) {
                when (commandlineArgs[i]) {
                    "--test-data-dir" -> config.testDataDir = getParam(commandlineArgs, ++i, "--test-data-dir")
                    "--force-tty" -> config.ttyMode = TriBool.TRUE
                    "--no-tty" -> config.ttyMode = TriBool.FALSE
                    "--force-color" -> config.colorMode = TriBool.TRUE
                    "--no-color" -> config.colorMode = TriBool.FALSE
                    "--no-update-js" -> config.updateJs = false
                    "--no-complexity" -> config.analyzeComplexity = false
                    "--help", "-h" -> printUsageAndExit()
                    else -> throw RuntimeException("CL: Unrecognized argument: " +
                            commandlineArgs[i])
                }
                i++
            }
            if (config.testDataDir != null && !config.testDataDir.isEmpty()) {
                if (!Files.isDirectory(Paths.get(config.testDataDir))) {
                    throw RuntimeException(String.format(
                            "CL: --test_data_dir argument (%s) is not a directory",
                            config.testDataDir))
                }
            } else {
                config.testDataDir = TestUtils.getDefaultTestDataDirPath()
            }
            return config
        }
    }
}