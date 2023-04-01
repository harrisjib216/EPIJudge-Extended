package epi

import epi.test_framework.EpiTest

object TwoSum {
    @EpiTest(testDataFile = "two_sum.tsv")
    fun hasTwoSum(A: List<Integer?>?, t: Int): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TwoSum.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}