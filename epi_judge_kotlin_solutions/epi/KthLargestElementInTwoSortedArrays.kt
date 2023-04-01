package epi

import epi.test_framework.EpiTest

object KthLargestElementInTwoSortedArrays {
    @EpiTest(testDataFile = "kth_largest_element_in_two_sorted_arrays.tsv")
    fun findKthNTwoSortedArrays(A: List<Integer?>, B: List<Integer?>,
                                k: Int): Int {

        // Lower bound of elements we will choose in A.
        var b: Int = Math.max(0, k - B.size())
        // Upper bound of elements we will choose in A.
        var t: Int = Math.min(A.size(), k)
        while (b < t) {
            val x = b + (t - b) / 2
            val ax1: Int = if (x <= 0) Integer.MIN_VALUE else A[x - 1]
            val ax: Int = if (x >= A.size()) Integer.MAX_VALUE else A[x]
            val bkx1: Int = if (k - x <= 0) Integer.MIN_VALUE else B[k - x - 1]
            val bkx: Int = if (k - x >= B.size()) Integer.MAX_VALUE else B[k - x]
            if (ax < bkx1) {
                b = x + 1
            } else if (ax1 > bkx) {
                t = x - 1
            } else {
                // B.get(k - x - 1) <= A.get(x) && A.get(x - 1) < B.get(k - x).
                return Math.max(ax1, bkx1)
            }
        }
        val ab1: Int = if (b <= 0) Integer.MIN_VALUE else A[b - 1]
        val bkb1: Int = if (k - b - 1 < 0) Integer.MIN_VALUE else B[k - b - 1]
        return Math.max(ab1, bkb1)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthLargestElementInTwoSortedArrays.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}