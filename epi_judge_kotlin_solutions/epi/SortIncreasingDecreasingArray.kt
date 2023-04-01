package epi

import epi.test_framework.EpiTest

object SortIncreasingDecreasingArray {
    @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
    fun sortKIncreasingDecreasingArray(A: List<Integer>): List<Integer> {

        // Decomposes A into a set of sorted arrays.
        val sortedSubarrays: List<List<Integer>> = ArrayList()
        var subarrayType = SubarrayType.INCREASING
        var startIdx = 0
        for (i in 1..A.size()) {
            if (i == A.size() || A[i - 1] < A[i] && subarrayType == SubarrayType.DECREASING || A[i - 1] >= A[i] && subarrayType == SubarrayType.INCREASING) {
                val subList: List<Integer> = A.subList(startIdx, i)
                if (subarrayType == SubarrayType.DECREASING) {
                    Collections.reverse(subList)
                }
                sortedSubarrays.add(subList)
                startIdx = i
                subarrayType = if (subarrayType == SubarrayType.INCREASING) SubarrayType.DECREASING else SubarrayType.INCREASING
            }
        }
        return SortedArraysMerge.mergeSortedArrays(sortedSubarrays)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private enum class SubarrayType {
        INCREASING, DECREASING
    }
}