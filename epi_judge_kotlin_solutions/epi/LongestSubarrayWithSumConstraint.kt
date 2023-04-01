package epi

import epi.test_framework.EpiTest

object LongestSubarrayWithSumConstraint {
    @EpiTest(testDataFile = "longest_subarray_with_sum_constraint.tsv")
    fun findLongestSubarrayLessEqualK(A: List<Integer?>, k: Int): Int {

        // Build the prefix sum according to A.
        val prefixSum: List<Integer> = ArrayList()
        A.stream().reduce(0) { left, right ->
            prefixSum.add(left + right)
            left + right
        }

        // Early returns if the sum of A is smaller than or equal to k.
        if (prefixSum[prefixSum.size() - 1] <= k) {
            return A.size()
        }

        // Builds minPrefixSum.
        val minPrefixSum: List<Integer> = ArrayList(prefixSum)
        for (i in minPrefixSum.size() - 2 downTo 0) {
            minPrefixSum.set(i,
                    Math.min(minPrefixSum[i], minPrefixSum[i + 1]))
        }
        var a = 0
        var b = 0
        var maxLength = 0
        while (a < A.size() && b < A.size()) {
            val minCurrSum: Int = if (a > 0) minPrefixSum[b] - prefixSum[a - 1] else minPrefixSum[b]
            if (minCurrSum <= k) {
                val currLength = b - a + 1
                if (currLength > maxLength) {
                    maxLength = currLength
                }
                ++b
            } else { // minCurrSum > k.
                ++a
            }
        }
        return maxLength
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestSubarrayWithSumConstraint.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}