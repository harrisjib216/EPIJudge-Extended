package epi

import epi.test_framework.EpiTest

object MinimumWaitingTime {
    @EpiTest(testDataFile = "minimum_waiting_time.tsv")
    fun minimumTotalWaitingTime(serviceTimes: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumWaitingTime.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}