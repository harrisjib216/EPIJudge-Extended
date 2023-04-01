package epi

import epi.test_framework.EpiTest

object TwoSortedArraysMerge {
    fun mergeTwoSortedArrays(A: List<Integer>, m: Int,
                             B: List<Integer>, n: Int) {
        var a = m - 1
        var b = n - 1
        var writeIdx = m + n - 1
        while (a >= 0 && b >= 0) {
            A.set(writeIdx--, if (A[a] > B[b]) A[a--] else B[b--])
        }
        while (b >= 0) {
            A.set(writeIdx--, B[b--])
        }
    }

    @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
    fun mergeTwoSortedArraysWrapper(A: List<Integer>, m: Int, B: List<Integer>, n: Int): List<Integer> {
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