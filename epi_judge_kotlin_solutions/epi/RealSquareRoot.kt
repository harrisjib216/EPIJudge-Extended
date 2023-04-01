package epi

import epi.test_framework.EpiTest

object RealSquareRoot {
    @EpiTest(testDataFile = "real_square_root.tsv")
    fun squareRoot(x: Double): Double {

        // Decides the search range according to x's value relative to 1.0.
        var left: Double
        var right: Double
        if (x < 1.0) {
            left = x
            right = 1.0
        } else { // x >= 1.0.
            left = 1.0
            right = x
        }

        // Keeps searching as long as left != right, within tolerance.
        while (compare(left, right) != Ordering.EQUAL) {
            val mid = left + 0.5 * (right - left)
            val midSquared = mid * mid
            if (compare(midSquared, x) == Ordering.LARGER) {
                right = mid
            } else {
                left = mid
            }
        }
        return left
    }

    private fun compare(a: Double, b: Double): Ordering {
        val EPSILON = 0.000001
        // Uses normalization for precision problem.
        val diff: Double = (a - b) / Math.max(Math.abs(a), Math.abs(b))
        return if (diff < -EPSILON) Ordering.SMALLER else if (diff > EPSILON) Ordering.LARGER else Ordering.EQUAL
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RealSquareRoot.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private enum class Ordering {
        SMALLER, EQUAL, LARGER
    }
}