package epi

import epi.test_framework.EpiTest

object PowerSet {
    @EpiTest(testDataFile = "power_set.tsv")
    fun generatePowerSet(inputSet: List<Integer>): List<List<Integer>> {
        val powerSet: List<List<Integer>> = ArrayList()
        directedPowerSet(inputSet, 0, ArrayList<Integer>(), powerSet)
        return powerSet
    }

    // Generate all subsets whose intersection with inputSet[0], ...,
    // inputSet[toBeSelected - 1] is exactly selectedSoFar.
    private fun directedPowerSet(inputSet: List<Integer>, toBeSelected: Int,
                                 selectedSoFar: List<Integer>,
                                 powerSet: List<List<Integer>>) {
        if (toBeSelected == inputSet.size()) {
            powerSet.add(ArrayList(selectedSoFar))
            return
        }
        // Generate all subsets that contain inputSet[toBeSelected].
        selectedSoFar.add(inputSet[toBeSelected])
        directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet)
        // Generate all subsets that do not contain inputSet[toBeSelected].
        selectedSoFar.remove(selectedSoFar.size() - 1)
        directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet)
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