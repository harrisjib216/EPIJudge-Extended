package epi

import epi.test_framework.EpiTest

object Combinations {
    @EpiTest(testDataFile = "combinations.tsv")
    fun combinations(n: Int, k: Int): List<List<Integer>>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTestComparator
    fun comp(expected: List<List<Integer?>?>,
             result: List<List<Integer?>?>?): Boolean {
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
                        .runFromAnnotations(args, "Combinations.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}