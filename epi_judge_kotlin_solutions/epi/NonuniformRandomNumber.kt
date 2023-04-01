package epi

import epi.test_framework.EpiTest

object NonuniformRandomNumber {
    fun nonuniformRandomNumberGeneration(values: List<Integer>,
                                         probabilities: List<Double?>): Int {
        val prefixSumOfProbabilities: List<Double> = ArrayList()
        // Creating the endpoints for the intervals corresponding to the
        // probabilities.
        probabilities.stream().reduce(0.0) { left, right ->
            prefixSumOfProbabilities.add(left + right)
            left + right
        }
        val r = Random()
        // Get a random number in [0.0,1.0).
        val uniform01: Double = r.nextDouble()
        // Find the index of the interval that uniform01 lies in.
        val it: Int = Collections.binarySearch(prefixSumOfProbabilities, uniform01)
        return if (it < 0) {
            // We want the index of the first element in the array which is
            // greater than the key.
            //
            // When a key is not present in the array, Collections.binarySearch()
            // returns the negative of 1 plus the smallest index whose entry
            // is greater than the key.
            //
            // Therefore, if the return value is negative, by taking its absolute
            // value minus 1, we get the desired index.
            val intervalIdx: Int = Math.abs(it) - 1
            values[intervalIdx]
        } else {
            // We have it >= 0, i.e., uniform01 equals an entry
            // in prefixSumOfProbabilities.
            //
            // Because we uniform01 is a random double, the probability of it
            // equalling an endpoint in prefixSumOfProbabilities is exceedingly low.
            // However, it is not 0, so to be robust we must consider this case.
            values[it]
        }
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