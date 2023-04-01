package epi

import epi.test_framework.EpiTest

object Combinations {
    @EpiTest(testDataFile = "combinations.tsv")
    fun combinations(n: Int, k: Int): List<List<Integer>> {
        val result: List<List<Integer>> = ArrayList()
        directedCombinations(n, k, 1, ArrayList<Integer>(), result)
        return result
    }

    private fun directedCombinations(n: Int, k: Int, offset: Int,
                                     partialCombination: List<Integer>,
                                     result: List<List<Integer>>) {
        if (partialCombination.size() === k) {
            result.add(ArrayList(partialCombination))
            return
        }

        // Generate remaining combinations over {offset, ..., n - 1} of size
        // numRemaining.
        val numRemaining: Int = k - partialCombination.size()
        var i = offset
        while (i <= n && numRemaining <= n - i + 1) {
            partialCombination.add(i)
            directedCombinations(n, k, i + 1, partialCombination, result)
            partialCombination.remove(partialCombination.size() - 1)
            ++i
        }
    }

    @EpiTestComparator
    fun comp(expected: List<List<Integer?>?>,
             result: List<List<Integer?>?>?): Boolean {
        if (result == null) {
            return false
        }
        expected.sort(LexicographicalListComparator())
        result.sort(LexicographicalListComparator())
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Combinations.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}