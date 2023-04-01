package epi

import epi.test_framework.EpiTest

object Permutations {
    @EpiTest(testDataFile = "permutations.tsv")
    fun permutations(A: List<Integer>): List<List<Integer>> {
        val result: List<List<Integer>> = ArrayList()
        directedPermutations(0, A, result)
        return result
    }

    private fun directedPermutations(i: Int, A: List<Integer>,
                                     result: List<List<Integer>>) {
        if (i == A.size() - 1) {
            result.add(ArrayList(A))
            return
        }

        // Try every possibility for A[i].
        for (j in i until A.size()) {
            Collections.swap(A, i, j)
            // Generate all permutations for A.subList(i + 1, A.size()).
            directedPermutations(i + 1, A, result)
            Collections.swap(A, i, j)
        }
    }

    @EpiTestComparator
    fun comp(expected: List<List<Integer?>?>,
             result: List<List<Integer?>?>?): Boolean {
        if (result == null) {
            return false
        }
        for (l in expected) {
            Collections.sort(l)
        }
        expected.sort(LexicographicalListComparator())
        for (l in result) {
            Collections.sort(l)
        }
        result.sort(LexicographicalListComparator())
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Permutations.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}