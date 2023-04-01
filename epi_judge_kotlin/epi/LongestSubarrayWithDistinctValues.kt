package epi

import epi.test_framework.EpiTest

object LongestSubarrayWithDistinctValues {
    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
    fun longestSubarrayWithDistinctEntries(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}