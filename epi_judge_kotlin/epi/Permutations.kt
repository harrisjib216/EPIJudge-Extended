package epi

import epi.test_framework.EpiTest

object Permutations {
    @EpiTest(testDataFile = "permutations.tsv")
    fun permutations(A: List<Integer?>?): List<List<Integer>>? {
        // TODO - you fill in here.
        return null
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