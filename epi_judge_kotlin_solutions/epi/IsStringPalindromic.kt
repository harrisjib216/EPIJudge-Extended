package epi

import epi.test_framework.EpiTest

object IsStringPalindromic {
    @EpiTest(testDataFile = "is_string_palindromic.tsv")
    fun isPalindromic(s: String): Boolean {
        var i = 0
        var j: Int = s.length() - 1
        while (i < j) {
            if (s.charAt(i) !== s.charAt(j)) {
                return false
            }
            ++i
            --j
        }
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