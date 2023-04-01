package epi

import epi.test_framework.EpiTest

object MaximumSubarrayInCircularArray {
    @EpiTest(testDataFile = "maximum_subarray_in_circular_array.tsv")
    fun maxSubarraySumInCircular(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaximumSubarrayInCircularArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}