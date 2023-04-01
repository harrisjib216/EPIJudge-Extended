package epi

import epi.test_framework.EpiTest

object SearchShiftedSortedArray {
    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")
    fun searchSmallest(A: List<Integer>): Int {
        var left = 0
        var right: Int = A.size() - 1
        while (left < right) {
            val mid = left + (right - left) / 2
            if (A[mid] > A[right]) {
                // Minimum must be in A.subList(mid + 1, right + 1).
                left = mid + 1
            } else { // A.get(mid) < A.get(right).
                // Minimum cannot be in A.subList(mid + 1, right + 1) so it must be in
                // A.sublist(left, mid + 1).
                right = mid
            }
        }
        // Loop ends when left == right.
        return left
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}