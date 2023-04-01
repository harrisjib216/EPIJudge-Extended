package epi

import epi.test_framework.EpiTest

object EuclideanGcd {
    @EpiTest(testDataFile = "gcd.tsv")
    fun GCD(x: Long, y: Long): Long {
        return if (y == 0L) x else GCD(y, x % y)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EuclideanGcd.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}