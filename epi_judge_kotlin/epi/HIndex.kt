package epi

import epi.test_framework.EpiTest

object HIndex {
    @EpiTest(testDataFile = "h_index.tsv")
    fun hIndex(citations: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "HIndex.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}