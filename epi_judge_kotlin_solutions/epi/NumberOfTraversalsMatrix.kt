package epi

import epi.test_framework.EpiTest

object NumberOfTraversalsMatrix {
    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
    fun numberOfWays(n: Int, m: Int): Int {
        return computeNumberOfWaysToXY(n - 1, m - 1, Array(n) { IntArray(m) })
    }

    private fun computeNumberOfWaysToXY(x: Int, y: Int,
                                        numberOfWays: Array<IntArray>): Int {
        if (x == 0 && y == 0) {
            return 1
        }
        if (numberOfWays[x][y] == 0) {
            val waysTop = if (x == 0) 0 else computeNumberOfWaysToXY(x - 1, y, numberOfWays)
            val waysLeft = if (y == 0) 0 else computeNumberOfWaysToXY(x, y - 1, numberOfWays)
            numberOfWays[x][y] = waysTop + waysLeft
        }
        return numberOfWays[x][y]
    }

    private fun numberOfWaysSpaceEfficient(n: Int, m: Int): Int {
        var n = n
        var m = m
        if (n < m) {
            val temp = n
            n = m
            m = temp
        }
        val table: List<Integer> = ArrayList(Collections.nCopies(m, 1))
        for (i in 1 until n) {
            var prevRes = 0
            for (j in 0 until m) {
                table.set(j, table[j] + prevRes)
                prevRes = table[j]
            }
        }
        return table[m - 1]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}