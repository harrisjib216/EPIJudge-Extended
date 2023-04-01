package epi

import epi.test_framework.EpiTest

object ReverseBits {
    @EpiTest(testDataFile = "reverse_bits.tsv")
    fun reverseBits(x: Long): Long {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseBits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}