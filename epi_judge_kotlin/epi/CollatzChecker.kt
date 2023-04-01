package epi

import epi.test_framework.EpiTest

object CollatzChecker {
    @EpiTest(testDataFile = "collatz_checker.tsv")
    fun testCollatzConjecture(n: Int): Boolean {
        // TODO - you fill in here.
        return false
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CollatzChecker.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}