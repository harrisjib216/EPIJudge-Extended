package epi

import epi.test_framework.EpiTest

object SearchRowColSortedMatrix {
    @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")
    fun matrixSearch(A: List<List<Integer?>?>?, x: Int): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}