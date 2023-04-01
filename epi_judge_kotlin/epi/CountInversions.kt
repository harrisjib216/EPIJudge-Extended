package epi

import epi.test_framework.EpiTest

object CountInversions {
    @EpiTest(testDataFile = "count_inversions.tsv")
    fun countInversions(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CountInversions.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}