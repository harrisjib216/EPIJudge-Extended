package epi

import epi.test_framework.EpiTest

object CountBits {
    @EpiTest(testDataFile = "count_bits.tsv")
    fun countBits(x: Int): Short {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CountBits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}