package epi

import epi.test_framework.EpiTest

object SunsetView {
    fun examineBuildingsWithSunset(sequence: Iterator<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    fun examineBuildingsWithSunsetWrapper(sequence: List<Integer?>): List<Integer>? {
        return examineBuildingsWithSunset(sequence.iterator())
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SunsetView.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}