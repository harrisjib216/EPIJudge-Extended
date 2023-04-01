package epi

import epi.test_framework.EpiTest

object LongestNondecreasingSubsequence {
    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
    fun longestNondecreasingSubsequenceLength(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}