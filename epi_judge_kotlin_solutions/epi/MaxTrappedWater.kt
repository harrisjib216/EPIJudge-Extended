package epi

import epi.test_framework.EpiTest

object MaxTrappedWater {
    @EpiTest(testDataFile = "max_trapped_water.tsv")
    fun getMaxTrappedWater(heights: List<Integer>): Int {
        var i = 0
        var j: Int = heights.size() - 1
        var maxWater = 0
        while (i < j) {
            val width = j - i
            maxWater = Math.max(maxWater, width * Math.min(heights[i], heights[j]))
            if (heights[i] > heights[j]) {
                --j
            } else { // heights.get(i) == heights.get(j).
                ++i
            }
        }
        return maxWater
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxTrappedWater.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}