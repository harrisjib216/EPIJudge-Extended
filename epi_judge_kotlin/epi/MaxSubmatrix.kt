package epi

import epi.test_framework.EpiTest

object MaxSubmatrix {
    @EpiTest(testDataFile = "max_submatrix.tsv")
    fun maxRectangleSubmatrix(A: List<List<Boolean?>?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxSubmatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}