package epi

import epi.test_framework.EpiTest

object IsStringPalindromicPunctuation {
    @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
    fun isPalindrome(s: String): Boolean {

        // i moves forward, and j moves backward.
        var i = 0
        var j: Int = s.length() - 1
        while (i < j) {
            // i and j both skip non-alphanumeric characters.
            while (!Character.isLetterOrDigit(s.charAt(i)) && i < j) {
                ++i
            }
            while (!Character.isLetterOrDigit(s.charAt(j)) && i < j) {
                --j
            }
            if (Character.toLowerCase(s.charAt(i++)) !==
                    Character.toLowerCase(s.charAt(j--))) {
                return false
            }
        }
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