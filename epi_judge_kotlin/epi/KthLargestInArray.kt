package epi

import epi.test_framework.EpiTest

object KthLargestInArray {
    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
    // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
    @EpiTest(testDataFile = "kth_largest_in_array.tsv")
    fun findKthLargest(k: Int, A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthLargestInArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}