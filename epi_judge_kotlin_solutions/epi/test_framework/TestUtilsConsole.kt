package epi.test_framework

import java.util.List

object TestUtilsConsole {
    private var caretAtLineStart = true
    fun escapeNewline(s: String): String {
        return s.replace("\n", "\\n").replace("\r", "\\r")
    }

    fun clearLineIfTty() {
        if (Platform.useTtyOutput()) {
            Platform.stdOutClearLine()
        } else {
            System.out.print('\n')
        }
    }

    fun printTestResult(testResult: TestResult?) {
        when (testResult) {
            PASSED -> ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_GREEN, "PASSED")
            FAILED -> ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_RED, "FAILED")
            TIMEOUT -> ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_BLUE, "TIMEOUT")
            UNKNOWN_EXCEPTION -> ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_RED,
                    "UNHANDLED EXCEPTION")
            STACK_OVERFLOW -> ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_RED,
                    "STACK OVERFLOW")
            else -> throw RuntimeException("Unknown TestResult")
        }
    }

    fun printTestInfo(testResult: TestResult, testNr: Int,
                      totalTests: Int, diagnostic: String,
                      timer: TestTimer?) {
        if (!caretAtLineStart) {
            clearLineIfTty()
        }
        val totalTestsStr: String = String.valueOf(totalTests)
        System.out.print("Test ")
        printTestResult(testResult)
        System.out.printf(" (%" + String.valueOf(totalTestsStr.length()) + "d/%s)",
                testNr, totalTestsStr)
        if (timer != null) {
            System.out.printf(" [%s]",
                    TestTimer.durationToString(timer.getMicroseconds()))
        }
        caretAtLineStart = false
        if (testResult !== TestResult.PASSED) {
            System.out.println(" $diagnostic")
            caretAtLineStart = true
        }
    }

    private fun genSpaces(count: Int): String {
        return String(CharArray(count)).replace('\u0000', ' ')
    }

    fun printFailedTest(paramNames: List<String>,
                        arguments: List<String>,
                        testFailure: TestFailure) {
        var maxColSize: Int = testFailure.getMaxPropertyNameLength()
        for (param in paramNames) {
            if (param.length() > maxColSize) {
                maxColSize = param.length()
            }
        }
        ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_YELLOW,
                "Arguments\n")
        for (i in 0 until arguments.size()) {
            System.out.print("\t")
            ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_YELLOW,
                    paramNames[i])
            System.out.printf(": %s%s\n",
                    genSpaces(maxColSize - paramNames[i].length()),
                    escapeNewline(arguments[i]))
        }
        val properties: List<TestFailure.Property> = testFailure.getProperties()
        if (!properties.isEmpty()) {
            ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_YELLOW,
                    "\nFailure info\n")
            for (prop in properties) {
                System.out.print("\t")
                ConsoleColor.printStdOutColored(ConsoleColor.Color.FG_YELLOW,
                        prop.name())
                System.out.printf(": %s%s\n",
                        genSpaces(maxColSize - prop.name().length()),
                        escapeNewline(String.valueOf(prop.value())))
            }
        }
    }

    fun showComplexityNotification() {
        if (Platform.useTtyOutput()) {
            System.out.print("Time complexity:\r")
        }
    }

    fun printPostRunStats(testsPassed: Int, totalTests: Int,
                          complexity: String,
                          durations: List<Long>) {
        if (!durations.isEmpty()) {
            if (!complexity.isEmpty()) {
                System.out.printf("Time complexity: %s\n", complexity)
            }
            val avgMedian: LongArray = TestTimer.avgAndMedianFromDuration(durations)
            System.out.printf("Average running time: %s\nMedian running time:  %s\n",
                    TestTimer.durationToString(avgMedian[0]),
                    TestTimer.durationToString(avgMedian[1]))
        }
        if (testsPassed < totalTests) {
            System.out.printf("*** You've passed %d/%d tests. ***\n", testsPassed,
                    totalTests)
        } else {
            System.out.println("*** You've passed ALL tests. Congratulations! ***")
        }
    }
}