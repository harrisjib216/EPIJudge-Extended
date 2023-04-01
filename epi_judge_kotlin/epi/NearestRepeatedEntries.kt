package epi

import epi.test_framework.EpiTest

object NearestRepeatedEntries {
    @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
    fun findNearestRepetition(paragraph: List<String?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}