package epi

import epi.test_framework.EpiTest

object LongestIncreasingSubarray {
    fun findLongestIncreasingSubarray(A: List<Integer?>?): Subarray {
        // TODO - you fill in here.
        return Subarray(0, 0)
    }

    @EpiTest(testDataFile = "longest_increasing_subarray.tsv")
    fun findLongestIncreasingSubarrayWrapper(A: List<Integer?>?): Int {
        val result = findLongestIncreasingSubarray(A)
        return result.end - result.start + 1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestIncreasingSubarray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    // Represent subarray by starting and ending indices, inclusive.
    class Subarray(start: Integer, end: Integer) {
        var start: Integer
        var end: Integer

        init {
            this.start = start
            this.end = end
        }
    }
}