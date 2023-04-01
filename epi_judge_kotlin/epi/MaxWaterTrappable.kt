package epi

import epi.test_framework.EpiTest

object MaxWaterTrappable {
    @EpiTest(testDataFile = "max_water_trappable.tsv")
    fun calculateTrappingWater(heights: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxWaterTrappable.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}