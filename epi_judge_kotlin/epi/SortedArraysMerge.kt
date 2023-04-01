package epi

import epi.test_framework.EpiTest

object SortedArraysMerge {
    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    fun mergeSortedArrays(sortedArrays: List<List<Integer?>?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArraysMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}