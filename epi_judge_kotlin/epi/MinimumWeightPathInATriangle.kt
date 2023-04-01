package epi

import epi.test_framework.EpiTest

object MinimumWeightPathInATriangle {
    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    fun minimumPathTotal(triangle: List<List<Integer?>?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumWeightPathInATriangle.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}