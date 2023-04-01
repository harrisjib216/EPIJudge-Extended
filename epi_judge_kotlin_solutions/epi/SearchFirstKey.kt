package epi

import epi.test_framework.EpiTest

object SearchFirstKey {
    @EpiTest(testDataFile = "search_first_key.tsv")
    fun searchFirstOfK(A: List<Integer>, k: Int): Int {
        var left = 0
        var right: Int = A.size() - 1
        var result = -1
        // A.subList(left, right + 1) is the candidate set.
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (A[mid] > k) {
                right = mid - 1
            } else if (A[mid] === k) {
                result = mid
                // Nothing to the right of mid can be the first occurrence of k.
                right = mid - 1
            } else { // A.get(mid) < k
                left = mid + 1
            }
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstKey.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}