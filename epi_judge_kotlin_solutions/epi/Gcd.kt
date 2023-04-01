package epi

import epi.test_framework.EpiTest

object Gcd {
    @EpiTest(testDataFile = "gcd.tsv")
    fun GCD(x: Long, y: Long): Long {
        if (x > y) {
            return GCD(y, x)
        } else if (x == 0L) {
            return y
        } else if (x and 1L == 0L && y and 1L == 0L) { // x and y are even.
            return GCD(x ushr 1, y ushr 1) shl 1
        } else if (x and 1L == 0L && y and 1L != 0L) { // x is even, y is odd.
            return GCD(x ushr 1, y)
        } else if (x and 1L != 0L && y and 1L == 0L) { // x is odd, y is even.
            return GCD(x, y ushr 1)
        }
        return GCD(x, y - x) // Both x and y are odd.
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Gcd.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}