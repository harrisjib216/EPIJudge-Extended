package epi

import epi.test_framework.EpiTest

object KthLargestElementInLongArray {
    fun findKthLargestUnknownLength(stream: Iterator<Integer?>?,
                                    k: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    @EpiTest(testDataFile = "kth_largest_element_in_long_array.tsv")
    fun findKthLargestUnknownLengthWrapper(stream: List<Integer?>,
                                           k: Int): Int {
        return findKthLargestUnknownLength(stream.iterator(), k)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthLargestElementInLongArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}