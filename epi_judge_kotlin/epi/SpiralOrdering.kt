package epi

import epi.test_framework.EpiTest

object SpiralOrdering {
    @EpiTest(testDataFile = "spiral_ordering.tsv")
    fun matrixInSpiralOrder(squareMatrix: List<List<Integer?>?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpiralOrdering.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}