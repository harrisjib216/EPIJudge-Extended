package epi

import epi.test_framework.EpiTest

object Anagrams {
    @EpiTest(testDataFile = "anagrams.tsv")
    fun findAnagrams(dictionary: List<String?>?): List<List<String>>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTestComparator
    fun comp(expected: List<List<String?>?>,
             result: List<List<String?>?>?): Boolean {
        if (result == null) {
            return false
        }
        for (l in expected) {
            Collections.sort(l)
        }
        expected.sort(LexicographicalListComparator())
        for (l in result) {
            Collections.sort(l)
        }
        result.sort(LexicographicalListComparator())
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Anagrams.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}