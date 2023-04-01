package epi

import epi.test_framework.EpiTest

object IsNumberPalindromic {
    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    fun isPalindromeNumber(x: Int): Boolean {
        var x = x
        if (x <= 0) {
            return x == 0
        }
        val numDigits = Math.floor(Math.log10(x)) as Int + 1
        var msdMask = Math.pow(10, numDigits - 1) as Int
        for (i in 0 until numDigits / 2) {
            if (x / msdMask != x % 10) {
                return false
            }
            x %= msdMask // Remove the most significant digit of x.
            x /= 10 // Remove the least significant digit of x.
            msdMask /= 100
        }
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsNumberPalindromic.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}