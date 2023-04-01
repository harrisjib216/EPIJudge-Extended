package epi

import epi.test_framework.EpiTest

object StringIntegerInterconversion {
    fun intToString(x: Int): String {
        // TODO - you fill in here.
        return "0"
    }

    fun stringToInt(s: String?): Int {
        // TODO - you fill in here.
        return 0
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    @Throws(TestFailure::class)
    fun wrapper(x: Int, s: String?) {
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