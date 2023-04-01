package epi

import epi.test_framework.EpiTest

object IsStringPalindromicPunctuation {
    @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
    fun isPalindrome(s: String?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}