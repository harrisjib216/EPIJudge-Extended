package epi

import epi.test_framework.EpiTest

object LongestSubarrayWithDistinctValues {
    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
    fun longestSubarrayWithDistinctEntries(A: List<Integer?>): Int {

        // Records the most recent occurrences of each entry.
        val mostRecentOccurrence: Map<Integer, Integer> = HashMap()
        var longestDupFreeSubarrayStartIdx = 0
        var result = 0
        for (i in 0 until A.size()) {
            val dupIdx: Integer = mostRecentOccurrence.put(A[i], i)
            // A.get(i) appeared before. Did it appear in the longest current
            // subarray?
            if (dupIdx != null) {
                if (dupIdx >= longestDupFreeSubarrayStartIdx) {
                    result = Math.max(result, i - longestDupFreeSubarrayStartIdx)
                    longestDupFreeSubarrayStartIdx = dupIdx + 1
                }
            }
        }
        return Math.max(result, A.size() - longestDupFreeSubarrayStartIdx)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}