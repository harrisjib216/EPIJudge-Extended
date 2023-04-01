package epi

import epi.test_framework.EpiTest

object EnumeratePalindromicDecompositions {
    @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
    fun palindromeDecompositions(text: String?): List<List<String>>? {
        // TODO - you fill in here.
        return null
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