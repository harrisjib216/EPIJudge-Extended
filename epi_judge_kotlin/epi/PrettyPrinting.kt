package epi

import epi.test_framework.EpiTest

object PrettyPrinting {
    @EpiTest(testDataFile = "pretty_printing.tsv")
    fun minimumMessiness(words: List<String?>?, lineLength: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrettyPrinting.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}