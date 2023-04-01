package epi

import epi.test_framework.EpiTest

object IntersectSortedArrays {
    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    fun intersectTwoSortedArrays(A: List<Integer>,
                                 B: List<Integer?>): List<Integer> {
        val intersectionAB: List<Integer> = ArrayList()
        for (i in 0 until A.size()) {
            if ((i == 0 || !A[i].equals(A[i - 1])) && B.contains(A[i])) {
                intersectionAB.add(A[i])
            }
        }
        return intersectionAB
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntersectSortedArrays.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}