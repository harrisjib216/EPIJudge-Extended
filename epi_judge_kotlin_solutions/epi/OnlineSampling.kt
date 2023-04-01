package epi

import epi.test_framework.EpiTest

object OnlineSampling {
    // Assumption: there are at least k elements in the stream.
    fun onlineRandomSample(stream: Iterator<Integer?>,
                           k: Int): List<Integer> {
        val runningSample: List<Integer> = ArrayList(k)
        // Stores the first k elements.
        var i = 0
        while (stream.hasNext() && i < k) {
            runningSample.add(stream.next())
            ++i
        }

        // Have read the first k elements.
        var numSeenSoFar = k
        val randIdxGen = Random()
        while (stream.hasNext()) {
            val x: Integer? = stream.next()
            ++numSeenSoFar
            // Generate a random number in [0, numSeenSoFar], and if this number is in
            // [0, k - 1], we replace that element from the sample with x.
            val idxToReplace: Int = randIdxGen.nextInt(numSeenSoFar)
            if (idxToReplace < k) {
                runningSample.set(idxToReplace, x)
            }
        }
        return runningSample
    }

    @Throws(Exception::class)
    private fun onlineRandomSampleRunner(executor: TimedExecutor,
                                         A: List<Integer>, k: Int): Boolean {
        val results: List<List<Integer>> = ArrayList()
        executor.run {
            for (i in 0..999999) {
                results.add(onlineRandomSample(A.iterator(), k))
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

    @EpiTest(testDataFile = "online_sampling.tsv")
    @Throws(Exception::class)
    fun onlineRandomSampleWrapper(executor: TimedExecutor,
                                  stream: List<Integer>, k: Int) {
        RandomSequenceChecker.runFuncWithRetries { onlineRandomSampleRunner(executor, stream, k) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "OnlineSampling.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}