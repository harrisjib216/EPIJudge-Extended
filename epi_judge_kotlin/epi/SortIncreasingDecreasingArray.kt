package epi

import epi.test_framework.EpiTest

object SortIncreasingDecreasingArray {
    @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
    fun sortKIncreasingDecreasingArray(A: List<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}