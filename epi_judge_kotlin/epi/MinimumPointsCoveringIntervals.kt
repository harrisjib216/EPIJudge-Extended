package epi

import epi.test_framework.EpiTest

object MinimumPointsCoveringIntervals {
    @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")
    fun findMinimumVisits(intervals: List<Interval?>?): Integer {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Interval(var left: Int, var right: Int)
}