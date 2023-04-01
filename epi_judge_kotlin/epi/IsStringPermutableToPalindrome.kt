package epi

import epi.test_framework.EpiTest

object IsStringPermutableToPalindrome {
    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")
    fun canFormPalindrome(s: String?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}