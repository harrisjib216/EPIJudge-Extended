package epi

import epi.test_framework.EpiTest

object LongestIncreasingSubarray {
    fun findLongestIncreasingSubarray(A: List<Integer>): Subarray {
        var maxLength = 1
        var result = Subarray(0, 0)
        var i = 0
        while (i < A.size() - maxLength) {
            // Backward check and skip if A[j - 1] >= A[j].
            var isSkippable = false
            for (j in i + maxLength downTo i + 1) {
                if (A[j - 1] >= A[j]) {
                    i = j
                    isSkippable = true
                    break
                }
            }

            // Forward check if it is not skippable.
            if (!isSkippable) {
                i += maxLength
                while (i < A.size() && A[i - 1] < A[i]) {
                    ++i
                    ++maxLength
                }
                result = Subarray(i - maxLength, i - 1)
            }
        }
        return result
    }

    @EpiTest(testDataFile = "longest_increasing_subarray.tsv")
    fun findLongestIncreasingSubarrayWrapper(A: List<Integer>): Int {
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