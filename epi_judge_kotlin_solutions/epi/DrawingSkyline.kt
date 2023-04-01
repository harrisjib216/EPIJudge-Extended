package epi

import epi.test_framework.EpiTest

object DrawingSkyline {
    @EpiTest(testDataFile = "drawing_skyline.tsv")
    fun drawingSkylines(buildings: List<Rect>): List<Rect> {
        var minLeft: Int = Integer.MAX_VALUE
        var maxRight: Int = Integer.MIN_VALUE
        for (building in buildings) {
            minLeft = Math.min(minLeft, building.left)
            maxRight = Math.max(maxRight, building.right)
        }
        val heights: List<Integer> = ArrayList(Collections.nCopies(maxRight - minLeft + 1, 0))
        for (building in buildings) {
            for (i in building.left..building.right) {
                heights.set(i - minLeft,
                        Math.max(heights[i - minLeft], building.height))
            }
        }
        val result: List<Rect> = ArrayList()
        var left = 0
        for (i in 1 until heights.size()) {
            if (heights[i] !== heights[i - 1]) {
                result.add(
                        Rect(left + minLeft, i - 1 + minLeft, heights[i - 1]))
                left = i
            }
        }
        result.add(
                Rect(left + minLeft, maxRight, heights[heights.size() - 1]))
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DrawingSkyline.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class, Int::class])
    class Rect(var left: Int, var right: Int, var height: Int) {
        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val rectangle = o as Rect
            if (left != rectangle.left) {
                return false
            }
            return if (right != rectangle.right) {
                false
            } else height == rectangle.height
        }

        @Override
        override fun toString(): String {
            return "[$left, $right, $height]"
        }
    }
}