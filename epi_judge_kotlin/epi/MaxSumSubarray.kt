package epi

import epi.test_framework.EpiTest

object MaxSumSubarray {
    @EpiTest(testDataFile = "max_sum_subarray.tsv")
    fun findMaximumSubarray(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return -1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxSumSubarray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}