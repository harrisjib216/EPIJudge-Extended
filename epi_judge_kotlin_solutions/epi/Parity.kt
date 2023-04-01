package epi

import epi.test_framework.EpiTest

object Parity {
    @EpiTest(testDataFile = "parity.tsv")
    fun parity(x: Long): Short {
        var x = x
        var result: Short = 0
        while (x != 0L) {
            result = (result.toLong() xor (x and 1L)).toShort()
            x = x ushr 1
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Parity.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}