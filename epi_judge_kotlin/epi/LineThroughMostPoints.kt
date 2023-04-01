package epi

import epi.test_framework.EpiTest

object LineThroughMostPoints {
    @EpiTest(testDataFile = "line_through_most_points.tsv")
    fun findLineWithMostPoints(points: List<Point?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LineThroughMostPoints.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Point(var x: Int, var y: Int)
}