package epi

import epi.test_framework.EpiTest

object ThreeSum {
    @EpiTest(testDataFile = "three_sum.tsv")
    fun hasThreeSum(A: List<Integer?>?, t: Int): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ThreeSum.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}