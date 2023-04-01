package epi

import epi.test_framework.EpiTest

object AdvanceByOffsets {
    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    fun canReachEnd(maxAdvanceSteps: List<Integer>): Boolean {
        var furthestReachSoFar = 0
        val lastIndex: Int = maxAdvanceSteps.size() - 1
        var i = 0
        while (i <= furthestReachSoFar && furthestReachSoFar < lastIndex) {
            furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps[i])
            ++i
        }
        return furthestReachSoFar >= lastIndex
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AdvanceByOffsets.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}