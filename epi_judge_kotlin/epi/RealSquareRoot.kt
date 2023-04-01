package epi

import epi.test_framework.EpiTest

object RealSquareRoot {
    @EpiTest(testDataFile = "real_square_root.tsv")
    fun squareRoot(x: Double): Double {
        // TODO - you fill in here.
        return 0.0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RealSquareRoot.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}