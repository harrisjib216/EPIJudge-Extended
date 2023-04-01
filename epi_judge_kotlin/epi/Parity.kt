package epi

import epi.test_framework.EpiTest

object Parity {
    @EpiTest(testDataFile = "parity.tsv")
    fun parity(x: Long): Short {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Parity.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}