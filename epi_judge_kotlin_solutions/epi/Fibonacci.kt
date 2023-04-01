package epi

import epi.test_framework.EpiTest

object Fibonacci {
    private val cache: Map<Integer, Integer> = HashMap()
    @EpiTest(testDataFile = "fibonacci.tsv")
    fun fibonacci(n: Int): Int {
        if (n <= 1) {
            return n
        }
        cache.putIfAbsent(n, fibonacci(n - 2) + fibonacci(n - 1))
        return cache[n]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Fibonacci.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}