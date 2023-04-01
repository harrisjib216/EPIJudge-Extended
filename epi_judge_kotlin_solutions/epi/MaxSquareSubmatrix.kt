package epi

import epi.test_framework.EpiTest

object MaxSquareSubmatrix {
    @EpiTest(testDataFile = "max_square_submatrix.tsv")
    fun maxSquareSubmatrix(A: List<List<Boolean>>): Int {

        // DP table stores (h, w) for each (i, j).
        val table: List<List<MaxHW>> = ArrayList(A.size())
        for (a in A) {
            table.add(
                    ArrayList(Collections.nCopies(a.size(), MaxHW(0, 0))))
        }
        for (i in A.size() - 1 downTo 0) {
            for (j in A[i].size() - 1 downTo 0) {
                // Find the largest h such that (i, j) to (i + h - 1, j) are feasible.
                // Find the largest w such that (i, j) to (i, j + w - 1) are feasible.
                table[i].set(
                        j, if (A[i][j]) MaxHW(
                        if (i + 1 < A.size()) table[i + 1][j].h + 1 else 1,
                        if (j + 1 < A[i].size()) table[i][j + 1].w + 1 else 1) else MaxHW(0, 0))
            }
        }

        // A table stores the length of the largest square for each (i, j).
        val s: List<List<Integer>> = ArrayList(A.size())
        for (a in A) {
            s.add(ArrayList(Collections.nCopies(a.size(), 0)))
        }
        var maxSquareArea = 0
        for (i in A.size() - 1 downTo 0) {
            for (j in A[i].size() - 1 downTo 0) {
                var side: Int = Math.min(table[i][j].h, table[i][j].w)
                if (A[i][j]) {
                    // Get the length of largest square with bottom-left corner (i, j).
                    if (i + 1 < A.size() && j + 1 < A[i + 1].size()) {
                        side = Math.min(s[i + 1][j + 1] + 1, side)
                    }
                    s[i].set(j, side)
                    maxSquareArea = Math.max(maxSquareArea, side * side)
                }
            }
        }
        return maxSquareArea
    }

    fun maxSquareSubmatrixSpaceEfficient(A: List<List<Boolean>>): Int {
        var pre: List<Integer?> = ArrayList(Collections.nCopies(A[0].size(), 0))
        var maxSide = 0
        for (row in A) {
            val curr: List<Integer> = ArrayList(A[0].size())
            for (cell in row) {
                curr.add(if (cell) 1 else 0)
            }
            for (j in 1 until curr.size()) {
                curr.set(j,
                        curr[j] * (Math.min(Math.min(pre[j - 1], pre[j]),
                                curr[j - 1]) +
                                1))
            }
            maxSide = Math.max(maxSide, Collections.max(curr))
            pre = curr
        }
        return maxSide * maxSide
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxSquareSubmatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class MaxHW(var h: Int, var w: Int)
}