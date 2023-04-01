package epi

import epi.test_framework.EpiTest

object EvenOddArray {
    fun evenOdd(A: List<Integer?>?) {
        // TODO - you fill in here.
        return
    }

    @EpiTest(testDataFile = "even_odd_array.tsv")
    @Throws(Exception::class)
    fun evenOddWrapper(executor: TimedExecutor, A: List<Integer>) {
        val before: List<Integer> = ArrayList(A)
        executor.run { evenOdd(A) }
        var inOdd = false
        for (i in 0 until A.size()) {
            if (A[i] % 2 === 0) {
                if (inOdd) {
                    throw TestFailure("Even elements appear in odd part")
                }
            } else {
                inOdd = true
            }
        }
        val after: List<Integer> = ArrayList(A)
        Collections.sort(before)
        Collections.sort(after)
        if (!before.equals(after)) {
            throw TestFailure("Elements mismatch")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvenOddArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}