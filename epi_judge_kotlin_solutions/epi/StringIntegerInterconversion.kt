package epi

import epi.test_framework.EpiTest

object StringIntegerInterconversion {
    fun intToString(x: Int): String {
        var x = x
        var isNegative = false
        if (x < 0) {
            isNegative = true
        }
        val s = StringBuilder()
        do {
            s.append(('0' + Math.abs(x % 10)))
            x /= 10
        } while (x != 0)

        // Adds the negative sign back if isNegative.
        return s.append(if (isNegative) "-" else "").reverse().toString()
    }

    fun stringToInt(s: String): Int {
        return (if (s.charAt(0) === '-') -1 else 1) *
                s.substring(if (s.charAt(0) === '-' || s.charAt(0) === '+') 1 else 0)
                        .chars()
                        .reduce(0) { runningSum, c -> runningSum * 10 + c - '0'.code }
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    @Throws(TestFailure::class)
    fun wrapper(x: Int, s: String) {
        if (Integer.parseInt(intToString(x)) !== x) {
            throw TestFailure("Int to string conversion failed")
        }
        if (stringToInt(s) != x) {
            throw TestFailure("String to int conversion failed")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}