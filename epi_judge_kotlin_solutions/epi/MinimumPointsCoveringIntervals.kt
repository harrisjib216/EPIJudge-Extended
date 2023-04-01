package epi

import epi.test_framework.EpiTest

object MinimumPointsCoveringIntervals {
    @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")
    fun findMinimumVisits(intervals: List<Interval>): Integer {

        // Sort intervals based on the right endpoints.
        intervals.sort { i1, i2 -> Integer.compare(i1.right, i2.right) }
        var lastVisitTime: Integer = Integer.MIN_VALUE
        var numVisits: Integer = 0
        for (interval in intervals) {
            if (interval.left > lastVisitTime) {
                // The current right endpoint, lastVisitTime, will not cover any more
                // intervals.
                lastVisitTime = interval.right
                ++numVisits
            }
        }
        return numVisits
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