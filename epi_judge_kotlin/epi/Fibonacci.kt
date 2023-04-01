package epi

import epi.test_framework.EpiTest

object Fibonacci {
    @EpiTest(testDataFile = "fibonacci.tsv")
    fun fibonacci(n: Int): Int {
        // TODO - you fill in here.
        return -1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Fibonacci.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}