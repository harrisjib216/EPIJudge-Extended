package epi

import epi.test_framework.EpiTest

object MaxSquareSubmatrix {
    @EpiTest(testDataFile = "max_square_submatrix.tsv")
    fun maxSquareSubmatrix(A: List<List<Boolean?>?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxSquareSubmatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}