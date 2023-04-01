package epi

import epi.test_framework.EpiTest

object NumberOfTraversalsStaircase {
    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    fun numberOfWaysToTop(top: Int, maximumStep: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}