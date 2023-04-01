package epi

import epi.test_framework.EpiTest

object MaxSafeHeight {
    @EpiTest(testDataFile = "max_safe_height.tsv")
    fun getHeight(cases: Int, drops: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxSafeHeight.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}