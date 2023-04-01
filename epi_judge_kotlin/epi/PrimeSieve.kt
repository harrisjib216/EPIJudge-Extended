package epi

import epi.test_framework.EpiTest

object PrimeSieve {
    @EpiTest(testDataFile = "prime_sieve.tsv") // Given n, return all primes up to and including n.
    fun generatePrimes(n: Int): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimeSieve.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}