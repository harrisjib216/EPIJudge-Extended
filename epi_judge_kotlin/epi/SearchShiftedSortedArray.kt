package epi

import epi.test_framework.EpiTest

object SearchShiftedSortedArray {
    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")
    fun searchSmallest(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}