package epi

import epi.test_framework.EpiTest

object EnumeratePalindromicDecompositions {
    @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
    fun palindromeDecompositions(text: String): List<List<String>> {
        val result: List<List<String>> = ArrayList()
        directedPalindromeDecompositions(text,  /*offset=*/0,
                ArrayList<String>(), result)
        return result
    }

    private fun directedPalindromeDecompositions(text: String, offset: Int,
                                                 partialPartition: List<String>,
                                                 result: List<List<String>>) {
        if (offset == text.length()) {
            result.add(ArrayList(partialPartition))
            return
        }
        for (i in offset + 1..text.length()) {
            val prefix: String = text.substring(offset, i)
            if (isPalindrome(prefix)) {
                partialPartition.add(prefix)
                directedPalindromeDecompositions(text, i, partialPartition, result)
                partialPartition.remove(partialPartition.size() - 1)
            }
        }
    }

    private fun isPalindrome(prefix: String): Boolean {
        var i = 0
        var j: Int = prefix.length() - 1
        while (i < j) {
            if (prefix.charAt(i) !== prefix.charAt(j)) {
                return false
            }
            ++i
            --j
        }
        return true
    }

    @EpiTestComparator
    fun comp(expected: List<List<String?>?>,
             result: List<List<String?>?>?): Boolean {
        if (result == null) {
            return false
        }
        expected.sort(LexicographicalListComparator())
        result.sort(LexicographicalListComparator())
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EnumeratePalindromicDecompositions.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}