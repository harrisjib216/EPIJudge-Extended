package epi

import epi.test_framework.EpiTest

object CountInversions {
    @EpiTest(testDataFile = "count_inversions.tsv")
    fun countInversions(A: List<Integer>): Int {
        return countSubarrayInversions(A, 0, A.size())
    }

    // Return the number of inversions in A.subList(start, finish).
    private fun countSubarrayInversions(A: List<Integer>, start: Int,
                                        finish: Int): Int {
        if (finish - start <= 1) {
            return 0
        }
        val mid = start + (finish - start) / 2
        return countSubarrayInversions(A, start, mid) +
                countSubarrayInversions(A, mid, finish) +
                mergeSortAndCountInversionsAcrossSubarrays(A, start, mid, finish)
    }

    // Merge two sorted sublists AsubList(start, mid) and A.subList(mid, finish)
    // into A.subList(start, finish) and return the number of inversions across
    // A.subList(start, mid) and A.subList(mid, finish).
    private fun mergeSortAndCountInversionsAcrossSubarrays(A: List<Integer>,
                                                           start: Int,
                                                           mid: Int,
                                                           finish: Int): Int {
        var start = start
        val sortedA: List<Integer> = ArrayList()
        var leftStart = start
        var rightStart = mid
        var inversionCount = 0
        while (leftStart < mid && rightStart < finish) {
            if (Integer.compare(A[leftStart], A[rightStart]) <= 0) {
                sortedA.add(A[leftStart++])
            } else {
                // A.subList(leftStart, mid) are the inversions of A[rightStart].
                inversionCount += mid - leftStart
                sortedA.add(A[rightStart++])
            }
        }
        sortedA.addAll(A.subList(leftStart, mid))
        sortedA.addAll(A.subList(rightStart, finish))

        // Updates A with sortedA.
        for (t in sortedA) {
            A.set(start++, t)
        }
        return inversionCount
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CountInversions.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}