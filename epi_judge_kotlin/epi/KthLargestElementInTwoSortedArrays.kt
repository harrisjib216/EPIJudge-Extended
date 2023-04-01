package epi

import epi.test_framework.EpiTest

object KthLargestElementInTwoSortedArrays {
    @EpiTest(testDataFile = "kth_largest_element_in_two_sorted_arrays.tsv")
    fun findKthNTwoSortedArrays(A: List<Integer?>?, B: List<Integer?>?,
                                k: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthLargestElementInTwoSortedArrays.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}