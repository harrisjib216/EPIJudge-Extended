package epi

import epi.test_framework.EpiTest

object MinimumWaitingTime {
    @EpiTest(testDataFile = "minimum_waiting_time.tsv")
    fun minimumTotalWaitingTime(serviceTimes: List<Integer>): Int {

        // Sort the service times in increasing order.
        Collections.sort(serviceTimes)
        var totalWaitingTime = 0
        for (i in 0 until serviceTimes.size()) {
            val numRemainingQueries: Int = serviceTimes.size() - (i + 1)
            totalWaitingTime += serviceTimes[i] * numRemainingQueries
        }
        return totalWaitingTime
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumWaitingTime.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}