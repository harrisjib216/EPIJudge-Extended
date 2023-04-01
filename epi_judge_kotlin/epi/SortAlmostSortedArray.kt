package epi

import epi.test_framework.EpiTest

object SortAlmostSortedArray {
    fun sortApproximatelySortedData(sequence: Iterator<Integer?>?, k: Int): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
    fun sortApproximatelySortedDataWrapper(sequence: List<Integer?>, k: Int): List<Integer>? {
        return sortApproximatelySortedData(sequence.iterator(), k)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}