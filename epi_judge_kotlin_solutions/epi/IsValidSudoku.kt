package epi

import epi.test_framework.EpiTest

object IsValidSudoku {
    @EpiTest(testDataFile = "is_valid_sudoku.tsv") // Check if a partially filled matrix has any conflicts.
    fun isValidSudoku(partialAssignment: List<List<Integer>>): Boolean {

        // Check row constraints.
        for (i in 0 until partialAssignment.size()) {
            if (hasDuplicate(partialAssignment, i, i + 1, 0,
                            partialAssignment.size())) {
                return false
            }
        }

        // Check column constraints.
        for (j in 0 until partialAssignment.size()) {
            if (hasDuplicate(partialAssignment, 0, partialAssignment.size(), j,
                            j + 1)) {
                return false
            }
        }

        // Check region constraints.
        val regionSize = Math.sqrt(partialAssignment.size()) as Int
        for (I in 0 until regionSize) {
            for (J in 0 until regionSize) {
                if (hasDuplicate(partialAssignment, regionSize * I,
                                regionSize * (I + 1), regionSize * J,
                                regionSize * (J + 1))) {
                    return false
                }
            }
        }
        return true
    }

    // Return true if subarray
    // partialAssignment[startRow, endRow][startCol, endCol] contains any
    // duplicates in {1, 2, ..., partialAssignment.size()}; otherwise return
    // false.
    private fun hasDuplicate(partialAssignment: List<List<Integer>>,
                             startRow: Int, endRow: Int, startCol: Int,
                             endCol: Int): Boolean {
        val isPresent: List<Boolean> = ArrayList(
                Collections.nCopies(partialAssignment.size() + 1, false))
        for (i in startRow until endRow) {
            for (j in startCol until endCol) {
                if (partialAssignment[i][j] !== 0 &&
                        isPresent[partialAssignment[i][j]]) {
                    return true
                }
                isPresent.set(partialAssignment[i][j], true)
            }
        }
        return false
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidSudoku.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}