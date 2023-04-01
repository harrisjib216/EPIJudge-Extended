package epi

import epi.test_framework.EpiTest

object NQueens {
    @EpiTest(testDataFile = "n_queens.tsv")
    fun nQueens(n: Int): List<List<Integer>> {
        val result: List<List<Integer>> = ArrayList()
        solveNQueens(n, 0, ArrayList<Integer>(), result)
        return result
    }

    private fun solveNQueens(n: Int, row: Int, colPlacement: List<Integer>,
                             result: List<List<Integer>>) {
        if (row == n) {
            // All queens are legally placed.
            result.add(ArrayList(colPlacement))
        } else {
            for (col in 0 until n) {
                colPlacement.add(col)
                if (isValid(colPlacement)) {
                    solveNQueens(n, row + 1, colPlacement, result)
                }
                colPlacement.remove(colPlacement.size() - 1)
            }
        }
    }

    // Test if a newly placed queen will conflict any earlier queens
    // placed before.
    private fun isValid(colPlacement: List<Integer>): Boolean {
        val rowID: Int = colPlacement.size() - 1
        for (i in 0 until rowID) {
            val diff: Int = Math.abs(colPlacement[i] - colPlacement[rowID])
            if (diff == 0 || diff == rowID - i) {
                return false
            }
        }
        return true
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