package epi

import epi.test_framework.EpiTest

object LongestContainedInterval {
    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    fun longestContainedRange(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestContainedInterval.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}