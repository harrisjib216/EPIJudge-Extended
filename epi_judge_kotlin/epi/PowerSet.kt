package epi

import epi.test_framework.EpiTest

object PowerSet {
    @EpiTest(testDataFile = "power_set.tsv")
    fun generatePowerSet(inputSet: List<Integer?>?): List<List<Integer>>? {
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
                        .runFromAnnotations(args, "PowerSet.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}