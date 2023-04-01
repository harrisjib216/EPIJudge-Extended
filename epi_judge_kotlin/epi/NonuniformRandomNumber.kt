package epi

import epi.test_framework.EpiTest

object NonuniformRandomNumber {
    fun nonuniformRandomNumberGeneration(values: List<Integer?>?,
                                         probabilities: List<Double?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    @Throws(Exception::class)
    private fun nonuniformRandomNumberGenerationRunner(
            executor: TimedExecutor, values: List<Integer>, probabilities: List<Double>): Boolean {
        val N = 1000000
        val results: List<Integer> = ArrayList(N)
        executor.run {
            for (i in 0 until N) {
                results.add(nonuniformRandomNumberGeneration(values, probabilities))
            }
        }
        val counts: Map<Integer, Integer> = HashMap()
        for (result in results) {
            counts.put(result, counts.getOrDefault(result, 0) + 1)
        }
        for (i in 0 until values.size()) {
            val v: Int = values[i]
            val p = probabilities[i]
            if (N * p < 50 || N * (1.0 - p) < 50) {
                continue
            }
            val sigma: Double = Math.sqrt(N * p * (1.0 - p))
            if (Math.abs(counts[v] - p * N) > 5 * sigma) {
                return false
            }
        }
        return true
    }

    @EpiTest(testDataFile = "nonuniform_random_number.tsv")
    @Throws(Exception::class)
    fun nonuniformRandomNumberGenerationWrapper(
            executor: TimedExecutor, values: List<Integer>, probabilities: List<Double>) {
        RandomSequenceChecker.runFuncWithRetries {
            nonuniformRandomNumberGenerationRunner(executor, values,
                    probabilities)
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NonuniformRandomNumber.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}