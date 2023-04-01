package epi

import epi.test_framework.EpiTest

object UniformRandomNumber {
    private fun zeroOneRandom(): Int {
        val gen = Random()
        return gen.nextInt(2)
    }

    fun uniformRandom(lowerBound: Int, upperBound: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    @Throws(Exception::class)
    private fun uniformRandomRunner(executor: TimedExecutor,
                                    lowerBound: Int, upperBound: Int): Boolean {
        val results: List<Integer> = ArrayList()
        executor.run {
            for (i in 0..99999) {
                results.add(uniformRandom(lowerBound, upperBound))
            }
        }
        val sequence: List<Integer> = ArrayList()
        for (result in results) {
            sequence.add(result - lowerBound)
        }
        return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
                sequence, upperBound - lowerBound + 1, 0.01)
    }

    @EpiTest(testDataFile = "uniform_random_number.tsv")
    @Throws(Exception::class)
    fun uniformRandomWrapper(executor: TimedExecutor,
                             lowerBound: Int, upperBound: Int) {
        RandomSequenceChecker.runFuncWithRetries { uniformRandomRunner(executor, lowerBound, upperBound) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "UniformRandomNumber.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}