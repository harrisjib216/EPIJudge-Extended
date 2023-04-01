package epi

import epi.test_framework.EpiTest

object Gcd {
    @EpiTest(testDataFile = "gcd.tsv")
    fun GCD(x: Long, y: Long): Long {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Gcd.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}