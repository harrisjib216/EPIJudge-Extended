package epi

import epi.test_framework.EpiTest

object BuyAndSellStockTwice {
    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    fun buyAndSellStockTwice(prices: List<Double>): Double {
        var maxTotalProfit = 0.0
        val firstBuySellProfits: List<Double> = ArrayList()
        var minPriceSoFar = Double.MAX_VALUE

        // Forward phase. For each day, we record maximum profit if we
        // sell on that day.
        for (i in 0 until prices.size()) {
            minPriceSoFar = Math.min(minPriceSoFar, prices[i])
            maxTotalProfit = Math.max(maxTotalProfit, prices[i] - minPriceSoFar)
            firstBuySellProfits.add(maxTotalProfit)
        }

        // Backward phase. For each day, find the maximum profit if we make
        // the second buy on that day.
        var maxPriceSoFar = Double.MIN_VALUE
        for (i in prices.size() - 1 downTo 1) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices[i])
            maxTotalProfit = Math.max(maxTotalProfit, maxPriceSoFar - prices[i] +
                    firstBuySellProfits[i])
        }
        return maxTotalProfit
    }

    private fun buyAndSellStockTwiceConstantSpace(prices: List<Double>): Double {
        val minPrices: List<Double> = List.of(Double.MAX_VALUE, Double.MAX_VALUE)
        val maxProfits: List<Double> = List.of(0.0, 0.0)
        for (price in prices) {
            for (i in 1 downTo 0) {
                maxProfits.set(i,
                        Math.max(maxProfits[i], price - minPrices[i]))
                minPrices.set(
                        i, Math.min(minPrices[i],
                        price - if (i - 1 >= 0) maxProfits[i - 1] else 0.0))
            }
        }
        return maxProfits[1]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}