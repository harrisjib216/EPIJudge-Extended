package epi

import epi.test_framework.EpiTest

object RandomSubset {
    // Returns a random k-sized subset of {0, 1, ..., n - 1}.
    fun randomSubset(n: Int, k: Int): List<Integer> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    @Throws(Exception::class)
    private fun randomSubsetRunner(executor: TimedExecutor, n: Int,
                                   k: Int): Boolean {
        val results: List<List<Integer>> = ArrayList()
        executor.run {
            for (i in 0..999999) {
                results.add(randomSubset(n, k))
            }
        }
        val totalPossibleOutcomes: Int = RandomSequenceChecker.binomialCoefficient(n, k)
        val A: List<Integer> = ArrayList(n)
        for (i in 0 until n) {
            A.add(i)
        }
        val combinations: List<List<Integer>> = ArrayList()
        for (i in 0 until RandomSequenceChecker.binomialCoefficient(n, k)) {
            combinations.add(RandomSequenceChecker.computeCombinationIdx(A, n, k, i))
        }
        val sequence: List<Integer> = ArrayList()
        for (result in results) {
            Collections.sort(result)
            sequence.add(combinations.indexOf(result))
        }
        return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
                sequence, totalPossibleOutcomes, 0.01)
    }

    @EpiTest(testDataFile = "random_subset.tsv")
    @Throws(Exception::class)
    fun randomSubsetWrapper(executor: TimedExecutor, n: Int, k: Int) {
        RandomSequenceChecker.runFuncWithRetries { randomSubsetRunner(executor, n, k) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RandomSubset.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}