package epi

import epi.test_framework.EpiTest

object IsNumberPalindromic {
    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    fun isPalindromeNumber(x: Int): Boolean {
        // TODO - you fill in here.
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