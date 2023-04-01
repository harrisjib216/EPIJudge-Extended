package epi

import epi.test_framework.EpiTest

object SwapBits {
    @EpiTest(testDataFile = "swap_bits.tsv")
    fun swapBits(x: Long, i: Int, j: Int): Long {

        // Extract the i-th and j-th bits, and see if they differ.
        var x = x
        if (x ushr i and 1L != x ushr j and 1L) {
            // i-th and j-th bits differ. We will swap them by flipping their values.
            // Select the bits to flip with bitMask. Since x^1 = 0 when x = 1 and 1
            // when x = 0, we can perform the flip XOR.
            val bitMask = 1L shl i or (1L shl j)
            x = x xor bitMask
        }
        return x
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SwapBits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}