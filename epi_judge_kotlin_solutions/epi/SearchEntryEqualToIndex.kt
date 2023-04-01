package epi

import epi.test_framework.EpiTest

object SearchEntryEqualToIndex {
    fun searchEntryEqualToItsIndex(A: List<Integer>): Int {
        var left = 0
        var right: Int = A.size() - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            val difference: Int = A[mid] - mid
            // A.get(mid) == mid if and only if difference == 0.
            if (difference == 0) {
                return mid
            } else if (difference > 0) {
                right = mid - 1
            } else { // difference < 0.
                left = mid + 1
            }
        }
        return -1
    }

    @EpiTest(testDataFile = "search_entry_equal_to_index.tsv")
    @Throws(Exception::class)
    fun searchEntryEqualToItsIndexWrapper(executor: TimedExecutor,
                                          A: List<Integer>) {
        val result: Int = executor.run { searchEntryEqualToItsIndex(A) }
        if (result != -1) {
            if (A[result] !== result) {
                throw TestFailure("Entry does not equal to its index")
            }
        } else {
            for (i in 0 until A.size()) {
                if (A[i] === i) {
                    throw TestFailure("There are entries which equal to its index")
                }
            }
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchEntryEqualToIndex.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}