package epi

import epi.test_framework.EpiTest

object TwoSortedArraysMerge {
    fun mergeTwoSortedArrays(A: List<Integer?>?, m: Int,
                             B: List<Integer?>?, n: Int) {
        // TODO - you fill in here.
        return
    }

    @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
    fun mergeTwoSortedArraysWrapper(A: List<Integer?>?, m: Int, B: List<Integer?>?, n: Int): List<Integer?>? {
        mergeTwoSortedArrays(A, m, B, n)
        return A
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}