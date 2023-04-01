package epi

import epi.test_framework.EpiTest

object NumberOfTraversalsMatrix {
    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
    fun numberOfWays(n: Int, m: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}