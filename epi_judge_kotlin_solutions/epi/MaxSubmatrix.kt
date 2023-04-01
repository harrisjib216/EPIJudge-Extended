package epi

import epi.test_framework.EpiTest

object MaxSubmatrix {
    @EpiTest(testDataFile = "max_submatrix.tsv")
    fun maxRectangleSubmatrix(A: List<List<Boolean>>): Int {

        // DP table stores (h, w) for each (i, j).
        val table = Array(A.size()) { arrayOfNulls<MaxHW>(A[0].size()) }
        for (i in A.size() - 1 downTo 0) {
            for (j in A[i].size() - 1 downTo 0) {
                // Find the largest h such that (i, j) to (i + h - 1, j) are feasible.
                // Find the largest w such that (i, j) to (i, j + w - 1) are feasible.
                table[i][j] = if (A[i][j]) MaxHW(if (i + 1 < A.size()) table[i + 1][j]!!.h + 1 else 1,
                        if (j + 1 < A[i].size()) table[i][j + 1]!!.w + 1 else 1) else MaxHW(0, 0)
            }
        }
        var maxRectangleArea = 0
        for (i in 0 until A.size()) {
            for (j in 0 until A[i].size()) {
                // Process (i, j) if it is feasible and is possible to update
                // maxRectangleArea.
                if (A[i][j] &&
                        table[i][j]!!.w * table[i][j]!!.h > maxRectangleArea) {
                    var minWidth: Int = Integer.MAX_VALUE
                    for (a in 0 until table[i][j]!!.h) {
                        minWidth = Math.min(minWidth, table[i + a][j]!!.w)
                        maxRectangleArea = Math.max(maxRectangleArea, minWidth * (a + 1))
                    }
                }
            }
        }
        return maxRectangleArea
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxSubmatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class MaxHW(var h: Int, var w: Int)
}