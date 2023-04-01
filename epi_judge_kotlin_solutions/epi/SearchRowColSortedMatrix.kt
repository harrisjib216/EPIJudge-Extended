package epi

import epi.test_framework.EpiTest

object SearchRowColSortedMatrix {
    @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")
    fun matrixSearch(A: List<List<Integer>>, x: Int): Boolean {
        var row = 0
        var col: Int = A[0].size() - 1 // Start from the top-right corner.
        // Keeps searching while there are unclassified rows and columns.
        while (row < A.size() && col >= 0) {
            if (A[row][col].equals(x)) {
                return true
            } else if (A[row][col] < x) {
                ++row // Eliminate this row.
            } else { // A.get(row).get(col) > x.
                --col // Eliminate this column.
            }
        }
        return false
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}