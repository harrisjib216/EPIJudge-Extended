package epi

import epi.test_framework.EpiTest

object PickingUpCoins {
    @EpiTest(testDataFile = "picking_up_coins.tsv")
    fun pickUpCoins(coins: List<Integer>): Int {
        return computeMaximumRevenueForRange(coins, 0, coins.size() - 1, Array(coins.size()) { IntArray(coins.size()) })
    }

    private fun computeMaximumRevenueForRange(coins: List<Integer>, a: Int, b: Int,
                                              maximumRevenueForRange: Array<IntArray>): Int {
        if (a > b) {
            // No coins left.
            return 0
        }
        if (maximumRevenueForRange[a][b] == 0) {
            val maximumRevenueA: Int = coins[a] +
                    Math.min(computeMaximumRevenueForRange(coins, a + 2, b,
                            maximumRevenueForRange),
                            computeMaximumRevenueForRange(coins, a + 1, b - 1,
                                    maximumRevenueForRange))
            val maximumRevenueB: Int = coins[b] +
                    Math.min(computeMaximumRevenueForRange(coins, a + 1, b - 1,
                            maximumRevenueForRange),
                            computeMaximumRevenueForRange(coins, a, b - 2,
                                    maximumRevenueForRange))
            maximumRevenueForRange[a][b] = Math.max(maximumRevenueA, maximumRevenueB)
        }
        return maximumRevenueForRange[a][b]
    }

    private fun maximumRevenueAlternative(coins: List<Integer>): Int {
        val prefixSum: List<Integer> = ArrayList()
        coins.stream().reduce(0) { left, right ->
            prefixSum.add(left + right)
            left + right
        }
        val maximumRevenueForRange: List<List<Integer>> = ArrayList(coins.size())
        for (i in 0 until coins.size()) {
            maximumRevenueForRange.add(
                    ArrayList(Collections.nCopies(coins.size(), -1)))
        }
        return maximumRevenueAlternativeHelper(coins, 0, coins.size() - 1,
                prefixSum, maximumRevenueForRange)
    }

    private fun maximumRevenueAlternativeHelper(coins: List<Integer>, a: Int, b: Int,
                                                prefixSum: List<Integer>,
                                                maximumRevenueForRange: List<List<Integer>>): Int {
        if (a > b) {
            return 0
        } else if (a == b) {
            return coins[a]
        }
        if (maximumRevenueForRange[a][b] === -1) {
            maximumRevenueForRange[a].set(
                    b, Math.max(
                    coins[a] + prefixSum[b] -
                            (if (a + 1 > 0) prefixSum[a] else 0) -
                            maximumRevenueAlternativeHelper(coins, a + 1, b, prefixSum,
                                    maximumRevenueForRange),
                    coins[b] + prefixSum[b - 1] -
                            (if (a > 0) prefixSum[a - 1] else 0) -
                            maximumRevenueAlternativeHelper(coins, a, b - 1, prefixSum,
                                    maximumRevenueForRange)))
        }
        return maximumRevenueForRange[a][b]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PickingUpCoins.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}