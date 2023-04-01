package epi

import epi.test_framework.EpiTest

object LongestSubarrayWithSumConstraint {
    @EpiTest(testDataFile = "longest_subarray_with_sum_constraint.tsv")
    fun findLongestSubarrayLessEqualK(A: List<Integer?>?, k: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestSubarrayWithSumConstraint.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}