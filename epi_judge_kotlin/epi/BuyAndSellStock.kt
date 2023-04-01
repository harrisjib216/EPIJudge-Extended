package epi

import epi.test_framework.EpiTest

object BuyAndSellStock {
    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    fun computeMaxProfit(prices: List<Double?>?): Double {
        // TODO - you fill in here.
        return 0.0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStock.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}