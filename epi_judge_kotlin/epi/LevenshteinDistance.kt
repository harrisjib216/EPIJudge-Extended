package epi

import epi.test_framework.EpiTest

object LevenshteinDistance {
    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    fun levenshteinDistance(A: String?, B: String?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LevenshteinDistance.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}