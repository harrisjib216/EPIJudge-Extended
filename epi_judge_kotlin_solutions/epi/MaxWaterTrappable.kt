package epi

import epi.test_framework.EpiTest

object MaxWaterTrappable {
    @EpiTest(testDataFile = "max_water_trappable.tsv")
    fun calculateTrappingWater(heights: List<Integer>): Int {

        // Finds the index with maximum height.
        val maxH = heights.indexOf(Collections.max(heights))
        return trappingWaterTillEnd(heights, 0, maxH, 1) +
                trappingWaterTillEnd(heights, heights.size() - 1, maxH, -1)
    }

    // Assume heights[end] is maximum height.
    private fun trappingWaterTillEnd(heights: List<Integer>, begin: Int,
                                     end: Int, step: Int): Int {
        var sum = 0
        var highestLevelSeen: Int = Integer.MIN_VALUE
        var i = begin
        while (i != end) {
            if (heights[i] >= highestLevelSeen) {
                highestLevelSeen = heights[i]
            } else {
                sum += highestLevelSeen - heights[i]
            }
            i += step
        }
        return sum
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxWaterTrappable.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}