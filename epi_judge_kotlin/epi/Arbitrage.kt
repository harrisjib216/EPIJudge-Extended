package epi

import epi.test_framework.EpiTest

object Arbitrage {
    @EpiTest(testDataFile = "arbitrage.tsv")
    fun isArbitrageExist(graph: List<List<Double?>?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Arbitrage.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}