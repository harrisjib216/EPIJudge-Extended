package epi

import epi.test_framework.EpiTest

object RomanToInteger {
    @EpiTest(testDataFile = "roman_to_integer.tsv")
    fun romanToInteger(s: String?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RomanToInteger.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}