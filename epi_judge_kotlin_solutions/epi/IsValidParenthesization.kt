package epi

import epi.test_framework.EpiTest

object IsValidParenthesization {
    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    fun isWellFormed(s: String): Boolean {
        val leftChars: Deque<Character> = ArrayDeque()
        val LOOKUP: Map<Character, Character?> = Map.of('(', ')', '{', '}', '[', ']')
        for (i in 0 until s.length()) {
            if (LOOKUP[s.charAt(i)] != null) {
                leftChars.addFirst(s.charAt(i))
            } else if (leftChars.isEmpty() ||
                    LOOKUP[leftChars.removeFirst()] !== s.charAt(i)) {
                return false // Unmatched right char.
            }
        }
        return leftChars.isEmpty()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidParenthesization.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}