package epi

import epi.test_framework.EpiTest

object IsValidSudoku {
    @EpiTest(testDataFile = "is_valid_sudoku.tsv") // Check if a partially filled matrix has any conflicts.
    fun isValidSudoku(partialAssignment: List<List<Integer?>?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidSudoku.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}