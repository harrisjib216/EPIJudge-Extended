package epi

import epi.test_framework.EpiTest

object LargestRectangleUnderSkyline {
    @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")
    fun calculateLargestRectangle(heights: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}