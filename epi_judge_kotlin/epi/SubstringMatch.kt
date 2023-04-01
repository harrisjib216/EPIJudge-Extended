package epi

import epi.test_framework.EpiTest

object SubstringMatch {
    @EpiTest(testDataFile = "substring_match.tsv") // Returns the index of the first character of the substring if found, -1
    // otherwise.
    fun rabinKarp(t: String?, s: String?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SubstringMatch.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}