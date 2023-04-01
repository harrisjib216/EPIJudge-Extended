package epi

import epi.test_framework.EpiTest

object NQueens {
    @EpiTest(testDataFile = "n_queens.tsv")
    fun nQueens(n: Int): List<List<Integer>>? {
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
                        .runFromAnnotations(args, "NQueens.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}