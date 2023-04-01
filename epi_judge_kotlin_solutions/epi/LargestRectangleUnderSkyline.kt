package epi

import epi.test_framework.EpiTest

object LargestRectangleUnderSkyline {
    @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")
    fun calculateLargestRectangle(heights: List<Integer>): Int {
        val pillarIndices: Deque<Integer> = ArrayDeque()
        var maxRectangleArea = 0
        // By iterating to heights.size() instead of heights.size() - 1, we can
        // uniformly handle the computation for rectangle area here.
        for (i in 0..heights.size()) {
            while (!pillarIndices.isEmpty() &&
                    isNewPillarOrReachEnd(heights, i, pillarIndices.peekFirst())) {
                val height: Int = heights[pillarIndices.removeFirst()]
                val width = if (pillarIndices.isEmpty()) i else i - pillarIndices.peekFirst() - 1
                maxRectangleArea = Math.max(maxRectangleArea, height * width)
            }
            pillarIndices.addFirst(i)
        }
        return maxRectangleArea
    }

    private fun isNewPillarOrReachEnd(heights: List<Integer>,
                                      currIdx: Int, lastPillarIdx: Int): Boolean {
        return if (currIdx < heights.size()) heights[currIdx] <= heights[lastPillarIdx] else true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}