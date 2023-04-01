package epi

import epi.test_framework.EpiTest

object IntSquareRoot {
    @EpiTest(testDataFile = "int_square_root.tsv")
    fun squareRoot(k: Int): Int {
        var left: Long = 0
        var right = k.toLong()
        // Candidate interval [left, right] where everything before left has
        // square <= k, and everything after right has square > k.
        while (left <= right) {
            val mid = left + (right - left) / 2
            val midSquared = mid * mid
            if (midSquared <= k) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left.toInt() - 1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntSquareRoot.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}