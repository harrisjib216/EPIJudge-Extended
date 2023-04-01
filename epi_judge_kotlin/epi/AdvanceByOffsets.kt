package epi

import epi.test_framework.EpiTest

object AdvanceByOffsets {
    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    fun canReachEnd(maxAdvanceSteps: List<Integer?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AdvanceByOffsets.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}