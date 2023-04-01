package epi

import epi.test_framework.EpiTest

object LongestSubstringWithMatchingParentheses {
    @EpiTest(testDataFile = "longest_substring_with_matching_parentheses.tsv")
    fun longestMatchingParentheses(s: String?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, "LongestSubstringWithMatchingParentheses.java",
                        object : Object() {}.getClass().getEnclosingClass())
                .ordinal())
    }
}