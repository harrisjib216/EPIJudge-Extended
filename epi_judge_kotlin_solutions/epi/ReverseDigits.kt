package epi

import epi.test_framework.EpiTest

object ReverseDigits {
    @EpiTest(testDataFile = "reverse_digits.tsv")
    fun reverse(x: Int): Long {
        var x = x
        var result: Long = 0
        while (x != 0) {
            // If x is an negative, x % 10 is the negative of least significant digit.
            // For example, -256 % 10 = -6.
            result = result * 10 + x % 10
            x /= 10
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseDigits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}