package epi

import epi.test_framework.EpiTest

object IntersectSortedArrays {
    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    fun intersectTwoSortedArrays(A: List<Integer?>?,
                                 B: List<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntersectSortedArrays.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}