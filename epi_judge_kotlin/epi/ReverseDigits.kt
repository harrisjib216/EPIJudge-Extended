package epi

import epi.test_framework.EpiTest

object ReverseDigits {
    @EpiTest(testDataFile = "reverse_digits.tsv")
    fun reverse(x: Int): Long {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseDigits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}