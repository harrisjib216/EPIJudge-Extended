package epi.test_framework

import java.util.ArrayList

object RandomSequenceChecker {
    private fun computeDeviationMultiplier(allowedFalseNegative: Double,
                                           numRvs: Int): Int {
        val individualRvError = allowedFalseNegative / numRvs
        val ERROR_BOUNDS = doubleArrayOf(1 - 0.682689492137086, 1 - 0.954499736103642,
                1 - 0.997300203936740, 1 - 0.999936657516334,
                1 - 0.999999426696856, 1 - 0.999999998026825,
                1 - 0.999999999997440)
        for (i in ERROR_BOUNDS.indices) {
            if (ERROR_BOUNDS[i] <= individualRvError) {
                return i + 1
            }
        }
        return ERROR_BOUNDS.size + 1
    }

    private fun checkFrequencies(seq: List<Integer>, n: Int,
                                 falseNegativeTolerance: Double): Boolean {
        val avg = seq.size() as Double / n
        val kIndiv = computeDeviationMultiplier(falseNegativeTolerance, n)
        val p = 1.0 / n
        val sigmaIndiv: Double = Math.sqrt(seq.size() * p * (1.0 - p))
        val kSigmaIndiv = kIndiv * sigmaIndiv
        // To make our testing meaningful "sufficiently large", we need to have
        // enough testing data.
        if (seq.size() * p < 50 || seq.size() * (1 - p) < 50) {
            return true // Sample size is too small so we cannot use normal
            // approximation
        }
        val indivFreqs: Map<Integer, Integer> = HashMap()
        for (a in seq) {
            indivFreqs.put(a, indivFreqs.getOrDefault(a, 0) + 1)
        }

        // Check that there are roughly seq.size()/n occurrences of key. By roughly
        // we mean the difference is less than k_sigma.
        return indivFreqs.values().stream().allMatch { freq -> Math.abs(avg - freq) <= kSigmaIndiv }
    }

    private fun checkPairsFrequencies(seq: List<Integer>, n: Int,
                                      falseNegativeTolerance: Double): Boolean {
        val seqPairs: List<Integer> = ArrayList()
        for (i in 1 until seq.size()) {
            seqPairs.add(seq[i - 1] * n + seq[i])
        }
        return checkFrequencies(seqPairs, n * n, falseNegativeTolerance)
    }

    private fun checkTriplesFrequencies(seq: List<Integer>, n: Int,
                                        falseNegativeTolerance: Double): Boolean {
        val seqTriples: List<Integer> = ArrayList()
        for (i in 2 until seq.size()) {
            seqTriples.add(seq[i - 2] * n * n + seq[i - 1] * n + seq[i])
        }
        return checkFrequencies(seqTriples, n * n * n, falseNegativeTolerance)
    }

    private fun checkBirthdaySpacings(seq: List<Integer>, n: Int): Boolean {
        val expectedAvgRepetitionLength = Math.ceil(Math.sqrt(Math.log(2.0) * 2.0 * n)) as Int
        val numberOfSubarrays: Int = seq.size() - expectedAvgRepetitionLength + 1
        val MIN_NUMBER_SUBARRAYS = 1000
        if (numberOfSubarrays < MIN_NUMBER_SUBARRAYS) {
            return true // Not enough subarrays for birthday spacing check
        }
        var numberOfSubarraysWithRepetitions = 0
        for (i in 0 until seq.size() - expectedAvgRepetitionLength) {
            val seqWindow: Set<Integer> = HashSet(seq.subList(i, i + expectedAvgRepetitionLength))
            numberOfSubarraysWithRepetitions += if (seqWindow.size() < expectedAvgRepetitionLength) 1 else 0
        }
        val COUNT_TOLERANCE = 0.4
        return COUNT_TOLERANCE * numberOfSubarrays <=
                numberOfSubarraysWithRepetitions
    }

    // seq is a sequence of integers, which should be in the range [0,n-1]. We
    // assume n << seq.size().
    fun checkSequenceIsUniformlyRandom(seq: List<Integer>, n: Int,
                                       falseNegativeTolerance: Double): Boolean {
        return checkFrequencies(seq, n, falseNegativeTolerance) &&
                checkPairsFrequencies(seq, n, falseNegativeTolerance) &&
                checkTriplesFrequencies(seq, n, falseNegativeTolerance) &&
                checkBirthdaySpacings(seq, n)
    }

    fun binomialCoefficient(n: Int, k: Int): Int {
        if (n < k) {
            return 0
        }
        return if (k == 0 || k == n) {
            1
        } else binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k)
    }

    // Get the mth combination in lexicographical order from A (n elements) chosen
    // k at a time.
    fun computeCombinationIdx(A: List<Integer?>, n: Int,
                              k: Int, m: Int): List<Integer> {
        val comb: List<Integer> = ArrayList()
        var a = n
        var b = k
        var x = binomialCoefficient(n, k) - 1 - m
        for (i in 0 until k) {
            --a
            while (binomialCoefficient(a, b) > x) {
                --a
            }
            comb.add(A[n - 1 - a])
            x -= binomialCoefficient(a, b)
            --b
        }
        return comb
    }

    @Throws(Exception::class)
    fun runFuncWithRetries(func: Callable<Boolean?>) {
        val NUM_RUNS = 5
        for (i in 0 until NUM_RUNS) {
            if (func.call()) {
                return
            }
        }
        throw TestFailure("Result is not a random permutation")
    }

    fun main(args: Array<String?>?) {
        val gen = Random()
        val seq: List<Integer> = ArrayList()
        for (i in 0..999999) {
            seq.add(gen.nextInt(10) + 1)
        }
        System.out.println(checkSequenceIsUniformlyRandom(seq, 10, 0.01))
        seq.clear()
        for (i in 0..999999) {
            seq.add(gen.nextInt(11) + 1)
        }
        System.out.println(checkSequenceIsUniformlyRandom(seq, 10, 0.01))
        seq.clear()
        for (i in 0..999999) {
            seq.add(i % 10)
        }
        System.out.println(checkSequenceIsUniformlyRandom(seq, 10, 0.01))
    }
}