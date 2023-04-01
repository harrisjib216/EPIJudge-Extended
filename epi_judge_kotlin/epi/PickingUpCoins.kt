package epi

import epi.test_framework.EpiTest

object PickingUpCoins {
    @EpiTest(testDataFile = "picking_up_coins.tsv")
    fun pickUpCoins(coins: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PickingUpCoins.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}