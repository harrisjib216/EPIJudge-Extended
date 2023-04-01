package epi

import epi.test_framework.EpiTest

object ClosestIntSameWeight {
    @EpiTest(testDataFile = "closest_int_same_weight.tsv")
    fun closestIntSameBitCount(x: Long): Long {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}