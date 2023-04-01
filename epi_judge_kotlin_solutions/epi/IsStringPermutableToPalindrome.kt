package epi

import epi.test_framework.EpiTest

object IsStringPermutableToPalindrome {
    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")
    fun canFormPalindrome(s: String): Boolean {
        val charsWithOddFrequency: Set<Character> = HashSet()
        for (i in 0 until s.length()) {
            val c: Char = s.charAt(i)
            if (charsWithOddFrequency.contains(c)) {
                // c now has appeared an even number of times.
                charsWithOddFrequency.remove(c)
            } else {
                // c now has appeared an odd number of times.
                charsWithOddFrequency.add(c)
            }
        }
        // A string can be permuted to form a palindrome if and only if the number
        // of chars whose frequencies is odd is at most 1.
        return charsWithOddFrequency.size() <= 1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}