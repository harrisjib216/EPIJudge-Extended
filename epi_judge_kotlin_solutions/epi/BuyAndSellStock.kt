package epi

import epi.test_framework.EpiTest

object BuyAndSellStock {
    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    fun computeMaxProfit(prices: List<Double>): Double {
        var minPrice = Double.MAX_VALUE
        var maxProfit = 0.0
        for (price in prices) {
            maxProfit = Math.max(maxProfit, price - minPrice)
            minPrice = Math.min(minPrice, price)
        }
        return maxProfit
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStock.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}