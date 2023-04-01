package epi

import epi.test_framework.EpiTest

object ClosestIntSameWeight {
    @EpiTest(testDataFile = "closest_int_same_weight.tsv")
    fun closestIntSameBitCount(x: Long): Long {
        var x = x
        val NUM_UNSIGNED_BITS = 63
        // x is assumed to be non-negative so we know the leading bit is 0. We
        // restrict to our attention to 63 LSBs.
        for (i in 0 until NUM_UNSIGNED_BITS - 1) {
            if (x ushr i and 1L != x ushr i + 1 and 1L) {
                x = x xor (1L shl i or (1L shl i)) + 1 // Swaps bit-i and bit-(i + 1).
                return x
            }
        }
        throw IllegalArgumentException("All bits are 0 or 1")
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}