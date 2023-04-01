package epi

import epi.test_framework.EpiTest

object OnlineMedian {
    fun onlineMedian(sequence: Iterator<Integer?>?): List<Double>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "online_median.tsv")
    fun onlineMedianWrapper(sequence: List<Integer?>): List<Double>? {
        return onlineMedian(sequence.iterator())
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "OnlineMedian.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}