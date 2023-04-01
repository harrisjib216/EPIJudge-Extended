package epi

import epi.test_framework.EpiTest

object MaxTrappedWater {
    @EpiTest(testDataFile = "max_trapped_water.tsv")
    fun getMaxTrappedWater(heights: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxTrappedWater.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}