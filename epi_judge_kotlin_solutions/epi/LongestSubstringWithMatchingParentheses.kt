package epi

import epi.test_framework.EpiTest

object LongestSubstringWithMatchingParentheses {
    @EpiTest(testDataFile = "longest_substring_with_matching_parentheses.tsv")
    fun longestMatchingParentheses(s: String): Int {
        var maxLength = 0
        var end = -1
        val leftParenthesesIndices: Deque<Integer> = ArrayDeque()
        for (i in 0 until s.length()) {
            if (s.charAt(i) === '(') {
                leftParenthesesIndices.addFirst(i)
            } else if (leftParenthesesIndices.isEmpty()) {
                end = i
            } else {
                leftParenthesesIndices.removeFirst()
                val start = if (leftParenthesesIndices.isEmpty()) end else leftParenthesesIndices.peekFirst()
                maxLength = Math.max(maxLength, i - start)
            }
        }
        return maxLength
    }

    private fun longestMatchingParenthesesConstantSpace(s: String): Int {
        return Math.max(parseFromSide(s, '(', 0, s.length(), 1),
                parseFromSide(s, ')', s.length() - 1, -1, -1))
    }

    private fun parseFromSide(s: String, paren: Char, begin: Int, end: Int,
                              dir: Int): Int {
        var maxLength = 0
        var numParensSoFar = 0
        var length = 0
        var i = begin
        while (i != end) {
            if (s.charAt(i) === paren) {
                ++numParensSoFar
                ++length
            } else { // s.charAt(i) != paren
                if (numParensSoFar <= 0) {
                    length = 0
                    numParensSoFar = length
                } else {
                    --numParensSoFar
                    ++length
                    if (numParensSoFar == 0) {
                        maxLength = Math.max(maxLength, length)
                    }
                }
            }
            i += dir
        }
        return maxLength
    }

    fun main(args: Array<String?>?) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, "LongestSubstringWithMatchingParentheses.java",
                        object : Object() {}.getClass().getEnclosingClass())
                .ordinal())
    }
}