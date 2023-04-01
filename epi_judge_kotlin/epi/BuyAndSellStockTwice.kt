package epi

import epi.test_framework.EpiTest

object BuyAndSellStockTwice {
    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    fun buyAndSellStockTwice(prices: List<Double?>?): Double {
        // TODO - you fill in here.
        return 0.0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}