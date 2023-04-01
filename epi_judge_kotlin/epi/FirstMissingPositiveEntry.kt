package epi

import epi.test_framework.EpiTest

object FirstMissingPositiveEntry {
    @EpiTest(testDataFile = "first_missing_positive_entry.tsv")
    fun findFirstMissingPositive(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "FirstMissingPositiveEntry.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}