package epi

import epi.test_framework.EpiTest

object OfflineSampling {
    fun randomSampling(k: Int, A: List<Integer?>?) {
        // TODO - you fill in here.
        return
    }

    @Throws(Exception::class)
    private fun randomSamplingRunner(executor: TimedExecutor, k: Int,
                                     A: List<Integer>): Boolean {
        val results: List<List<Integer>> = ArrayList()
        executor.run {
            for (i in 0..999999) {
                randomSampling(k, A)
                results.add(ArrayList(A.subList(0, k)))
            }
        }
        val totalPossibleOutcomes: Int = RandomSequenceChecker.binomialCoefficient(A.size(), k)
        Collections.sort(A)
        val combinations: List<List<Integer>> = ArrayList()
        for (i in 0 until RandomSequenceChecker.binomialCoefficient(A.size(), k)) {
            combinations.add(
                    RandomSequenceChecker.computeCombinationIdx(A, A.size(), k, i))
        }
        val sequence: List<Integer> = ArrayList()
        for (result in results) {
            Collections.sort(result)
            sequence.add(combinations.indexOf(result))
        }
        return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
                sequence, totalPossibleOutcomes, 0.01)
    }

    @EpiTest(testDataFile = "offline_sampling.tsv")
    @Throws(Exception::class)
    fun randomSamplingWrapper(executor: TimedExecutor, k: Int,
                              A: List<Integer>) {
        RandomSequenceChecker.runFuncWithRetries { randomSamplingRunner(executor, k, A) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "OfflineSampling.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}