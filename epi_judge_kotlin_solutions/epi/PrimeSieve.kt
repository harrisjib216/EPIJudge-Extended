package epi

import epi.test_framework.EpiTest

object PrimeSieve {
    @EpiTest(testDataFile = "prime_sieve.tsv") // Given n, return all primes up to and including n.
    fun generatePrimes(n: Int): List<Integer> {
        if (n < 2) {
            return Collections.emptyList()
        }
        val size = Math.floor(0.5 * (n - 3)) as Int + 1
        val primes: List<Integer> = ArrayList()
        primes.add(2)
        // isPrime.get(i) represents whether (2i + 3) is prime or not.
        // For example, isPrime.get(0) represents 3 is prime or not,
        // isPrime.get(1) represents 5, isPrime.get(2) represents 7, etc.
        // Initially, set each to true. Then use sieving to eliminate nonprimes.
        val isPrime: List<Boolean> = ArrayList(Collections.nCopies(size, true))
        for (i in 0 until size) {
            if (isPrime[i.toInt()]) {
                val p: Int = i.toInt() * 2 + 3
                primes.add(p)
                // Sieving from p^2, whose value is (4i^2 + 12i + 9). The index of this
                // value in isPrime is (2i^2 + 6i + 3) because isPrime.get(i) represents
                // 2i + 3.
                //
                // Note that we need to use long type for j because p^2 might overflow.
                var j: Long = i * i * 2 + 6 * i + 3
                while (j < size) {
                    isPrime.set(j.toInt(), false)
                    j += p.toLong()
                }
            }
        }
        return primes
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimeSieve.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}