package epi

import epi.test_framework.EpiTest

object SearchUnknownLengthArray {
    @EpiTest(testDataFile = "search_unknown_length_array.tsv")
    fun binarySearchUnknownLength(A: List<Integer>, k: Int): Int {

        // Find a range where k exists, if it's present.
        var p = 0
        while (true) {
            try {
                val idx = (1 shl p) - 1 // 2^p - 1.
                if (A[idx] === k) {
                    return idx
                } else if (A[idx] > k) {
                    break
                }
            } catch (e: IndexOutOfBoundsException) {
                break
            }
            ++p
        }

        // Binary search between indices 2^(p - 1) and 2^p - 2, inclusive.
        var left: Int = Math.max(0, 1 shl p - 1)
        var right = (1 shl p) - 2
        while (left <= right) {
            val mid = left + (right - left) / 2
            try {
                if (A[mid] === k) {
                    return mid
                } else if (A[mid] > k) {
                    right = mid - 1
                } else { // A.get(mid) < k
                    left = mid + 1
                }
            } catch (e: Exception) {
                right = mid - 1 // Search the left part if out-of-bound.
            }
        }
        return -1 // Nothing matched k.
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchUnknownLengthArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}