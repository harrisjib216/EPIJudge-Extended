package epi

import epi.test_framework.EpiTest

object CountBits {
    @EpiTest(testDataFile = "count_bits.tsv")
    fun countBits(x: Int): Short {
        var x = x
        var numBits: Short = 0
        while (x != 0) {
            (numBits += (x and 1).toShort()).toShort()
            x = x ushr 1
        }
        return numBits
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CountBits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}