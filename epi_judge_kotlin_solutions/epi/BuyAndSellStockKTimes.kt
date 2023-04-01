package epi

import epi.test_framework.EpiTest

object BuyAndSellStockKTimes {
    @EpiTest(testDataFile = "buy_and_sell_stock_k_times.tsv")
    fun buyAndSellStockKTimes(prices: List<Double>, k: Int): Double {
        if (k == 0) {
            return 0.0
        } else if (2 * k >= prices.size()) {
            return unlimitedPairsProfits(prices)
        }
        val minPrices: List<Double> = ArrayList(Collections.nCopies(k, Double.MAX_VALUE))
        val maxProfits: List<Double> = ArrayList(Collections.nCopies(k, 0.0))
        for (price in prices) {
            for (i in k - 1 downTo 0) {
                maxProfits.set(i,
                        Math.max(maxProfits[i], price - minPrices[i]))
                minPrices.set(i,
                        Math.min(minPrices[i],
                                price - if (i > 0) maxProfits[i - 1] else 0.0))
            }
        }
        return maxProfits[maxProfits.size() - 1]
    }

    private fun unlimitedPairsProfits(prices: List<Double>): Double {
        var profit = 0.0
        for (i in 1 until prices.size()) {
            profit += Math.max(0.0, prices[i] - prices[i - 1])
        }
        return profit
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStockKTimes.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}