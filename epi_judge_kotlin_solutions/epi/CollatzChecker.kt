package epi

import epi.test_framework.EpiTest

object CollatzChecker {
    @EpiTest(testDataFile = "collatz_checker.tsv")
    fun testCollatzConjecture(n: Int): Boolean {

        // Stores odd numbers already tested to converge to 1.
        val verifiedNumbers: Set<Long> = HashSet()

        // Starts from 3, since hypothesis holds trivially for 1 and 2.
        var i = 3
        while (i <= n) {
            val sequence: Set<Long> = HashSet()
            var testI = i.toLong()
            while (testI >= i) {
                if (!sequence.add(testI)) {
                    // We previously encountered testI, so the CollatzCheckerParallel
                    // sequence has fallen into a loop. This disproves the hypothesis, so
                    // we short-circuit, returning false.
                    return false
                }
                if (testI % 2 != 0L) { // Odd number
                    if (!verifiedNumbers.add(testI)) {
                        break // testI has already been verified to converge to 1.
                    }
                    val nextTestI = 3 * testI + 1 // Multiply by 3 and add 1.
                    if (nextTestI <= testI) {
                        throw ArithmeticException(
                                "CollatzCheckerParallel sequence overflow for $i")
                    }
                    testI = nextTestI
                } else {
                    testI /= 2 // Even number, halve it.
                }
            }
            i += 2
        }
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CollatzChecker.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}