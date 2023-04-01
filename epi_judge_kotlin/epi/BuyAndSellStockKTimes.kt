package epi

import epi.test_framework.EpiTest

object BuyAndSellStockKTimes {
    @EpiTest(testDataFile = "buy_and_sell_stock_k_times.tsv")
    fun buyAndSellStockKTimes(prices: List<Double?>?, k: Int): Double {
        // TODO - you fill in here.
        return 0.0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStockKTimes.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}