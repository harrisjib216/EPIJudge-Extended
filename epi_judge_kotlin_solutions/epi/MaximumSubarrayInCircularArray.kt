package epi

import epi.test_framework.EpiTest

object MaximumSubarrayInCircularArray {
    @EpiTest(testDataFile = "maximum_subarray_in_circular_array.tsv")
    fun maxSubarraySumInCircular(A: List<Integer>): Int {
        return Math.max(findMaxSubarray(A), findCircularMaxSubarray(A))
    }

    // Calculates the non-circular solution.
    private fun findMaxSubarray(A: List<Integer>): Int {
        var maximumTill = 0
        var maximum = 0
        for (a in A) {
            maximumTill = Math.max(a, a + maximumTill)
            maximum = Math.max(maximum, maximumTill)
        }
        return maximum
    }

    // Calculates the solution which is circular.
    private fun findCircularMaxSubarray(A: List<Integer>): Int {
        // Maximum subarray sum starts at index 0 and ends at or before index i.
        val maximumBegin: List<Integer> = computeRunningMaximum(A)

        // Maximum subarray sum starts at index i + 1 and ends at the last element.
        Collections.reverse(A)
        val maximumEnd: List<Integer> = computeRunningMaximum(A)
        maximumEnd.remove(maximumEnd.size() - 1)
        Collections.reverse(maximumEnd)
        maximumEnd.add(0)

        // Calculates the maximum subarray which is circular.
        return IntStream.range(0, A.size())
                .mapToObj { i -> maximumBegin[i] + maximumEnd[i] }
                .max(Integer::compare)
                .orElse(0)
    }

    private fun computeRunningMaximum(A: List<Integer>): List<Integer> {
        val runningMaximum: List<Integer> = ArrayList()
        var sum: Int = A[0]
        runningMaximum.add(sum)
        for (i in 1 until A.size()) {
            sum += A[i]
            runningMaximum.add(
                    Math.max(runningMaximum[runningMaximum.size() - 1], sum))
        }
        return runningMaximum
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaximumSubarrayInCircularArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}