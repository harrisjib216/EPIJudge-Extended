package epi

import epi.test_framework.EpiTest

object MaxSumSubarray {
    @EpiTest(testDataFile = "max_sum_subarray.tsv")
    fun findMaximumSubarray(A: List<Integer?>): Int {
        var maxSeen = 0
        var maxEnd = 0
        for (a in A) {
            maxEnd = Math.max(a, a + maxEnd)
            maxSeen = Math.max(maxSeen, maxEnd)
        }
        return maxSeen
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxSumSubarray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}