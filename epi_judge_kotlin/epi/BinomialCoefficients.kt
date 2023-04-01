package epi

import epi.test_framework.EpiTest

object BinomialCoefficients {
    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    fun computeBinomialCoefficient(n: Int, k: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BinomialCoefficients.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}