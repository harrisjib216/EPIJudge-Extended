package epi

import epi.test_framework.EpiTest

object MakingChange {
    @EpiTest(testDataFile = "making_change.tsv")
    fun changeMaking(cents: Int): Int {
        var cents = cents
        val COINS = intArrayOf(100, 50, 25, 10, 5, 1)
        var numCoins = 0
        for (i in COINS.indices) {
            numCoins += cents / COINS[i]
            cents %= COINS[i]
        }
        return numCoins
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MakingChange.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}