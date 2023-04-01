package epi

import epi.test_framework.EpiTest

object SwapBits {
    @EpiTest(testDataFile = "swap_bits.tsv")
    fun swapBits(x: Long, i: Int, j: Int): Long {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SwapBits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}