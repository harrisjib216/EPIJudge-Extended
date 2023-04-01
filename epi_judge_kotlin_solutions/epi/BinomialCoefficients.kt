package epi

import epi.test_framework.EpiTest

object BinomialCoefficients {
    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    fun computeBinomialCoefficient(n: Int, k: Int): Int {
        return computeXChooseY(n, k, Array(n + 1) { IntArray(k + 1) })
    }

    private fun computeXChooseY(x: Int, y: Int, xChooseY: Array<IntArray>): Int {
        if (y == 0 || x == y) {
            return 1
        }
        if (xChooseY[x][y] == 0) {
            val withoutY = computeXChooseY(x - 1, y, xChooseY)
            val withY = computeXChooseY(x - 1, y - 1, xChooseY)
            xChooseY[x][y] = withoutY + withY
        }
        return xChooseY[x][y]
    }

    private fun computeBinomialCoefficientsSpaceEfficient(n: Int, k: Int): Int {
        var k = k
        k = Math.min(k, n - k)
        val table: List<Integer> = ArrayList(Collections.nCopies(k + 1, 0))
        table.set(0, 1) // C(0, 0).
        // C(i, j) = C(i - 1, j) + C(i - 1, j - 1).
        for (i in 1..n) {
            for (j in Math.min(i, k) downTo 1) {
                table.set(j, table[j] + table[j - 1])
            }
        }
        return table[k]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BinomialCoefficients.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}