package epi

import epi.test_framework.EpiTest

object RandomPermutation {
    fun computeRandomPermutation(n: Int): List<Integer> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    private fun factorial(n: Int): Int {
        return if (n <= 1) 1 else n * factorial(n - 1)
    }

    private fun permutationIndex(perm: List<Integer>): Int {
        var idx = 0
        var n: Int = perm.size()
        for (i in 0 until perm.size()) {
            val a: Int = perm[i]
            idx += a * factorial(n - 1)
            for (j in i + 1 until perm.size()) {
                if (perm[j] > a) {
                    perm.set(j, perm[j] - 1)
                }
            }
            --n
        }
        return idx
    }

    @Throws(Exception::class)
    private fun computeRandomPermutationRunner(executor: TimedExecutor,
                                               n: Int): Boolean {
        val results: List<List<Integer>> = ArrayList()
        executor.run {
            for (i in 0..999999) {
                results.add(computeRandomPermutation(n))
            }
        }
        val sequence: List<Integer> = ArrayList()
        for (result in results) {
            sequence.add(permutationIndex(result))
        }
        return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
                sequence, factorial(n), 0.01)
    }

    @EpiTest(testDataFile = "random_permutation.tsv")
    @Throws(Exception::class)
    fun computeRandomPermutationWrapper(executor: TimedExecutor,
                                        n: Int) {
        RandomSequenceChecker.runFuncWithRetries { computeRandomPermutationRunner(executor, n) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RandomPermutation.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}