package epi

import epi.test_framework.EpiTest

object IsStringPalindromic {
    @EpiTest(testDataFile = "is_string_palindromic.tsv")
    fun isPalindromic(s: String?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPalindromic.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}