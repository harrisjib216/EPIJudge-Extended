package epi

import epi.test_framework.EpiTest

object LineThroughMostPoints {
    @EpiTest(testDataFile = "line_through_most_points.tsv")
    fun findLineWithMostPoints(points: List<Point>): Int {
        var result = 0
        for (i in 0 until points.size()) {
            val slopeTable: Map<Integer, Map<Integer, Integer>> = HashMap()
            var overlapPoints = 1
            for (j in i + 1 until points.size()) {
                if (points[i].x === points[j].x &&
                        points[i].y === points[j].y) {
                    ++overlapPoints
                } else {
                    var xDiff = points[i].x - points[j].x
                    var yDiff = points[i].y - points[j].y
                    if (xDiff == 0) {
                        // A vertical line with slope 1/0.
                        yDiff = 1
                    } else {
                        val gcd: Int = BigInteger.valueOf(xDiff)
                                .gcd(BigInteger.valueOf(yDiff))
                                .intValue()
                        xDiff /= gcd
                        yDiff /= gcd
                        if (xDiff < 0) {
                            xDiff = -xDiff
                            yDiff = -yDiff
                        }
                    }
                    slopeTable.putIfAbsent(xDiff, HashMap())
                    slopeTable[xDiff].put(
                            yDiff, slopeTable[xDiff].getOrDefault(yDiff, 0) + 1)
                }
            }
            result = Math.max(
                    result, overlapPoints +
                    if (slopeTable.isEmpty()) 0 else Collections.max(
                            Collections
                                    .max(slopeTable.values(),
                                            Comparator.comparingInt { m -> Collections.max(m.values()) })
                                    .values()))
        }
        return result
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