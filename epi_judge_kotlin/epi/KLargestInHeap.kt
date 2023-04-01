package epi

import epi.test_framework.EpiTest

object KLargestInHeap {
    @EpiTest(testDataFile = "k_largest_in_heap.tsv")
    fun kLargestInBinaryHeap(A: List<Integer?>?, k: Int): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTestComparator
    fun comp(expected: List<Integer?>, result: List<Integer?>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KLargestInHeap.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}