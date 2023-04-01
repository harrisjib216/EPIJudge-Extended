package epi

import epi.test_framework.EpiTest

object MakingChange {
    @EpiTest(testDataFile = "making_change.tsv")
    fun changeMaking(cents: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MakingChange.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}