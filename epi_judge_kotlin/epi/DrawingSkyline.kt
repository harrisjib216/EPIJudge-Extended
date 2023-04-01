package epi

import epi.test_framework.EpiTest

object DrawingSkyline {
    @EpiTest(testDataFile = "drawing_skyline.tsv")
    fun drawingSkylines(buildings: List<Rect?>?): List<Rect>? {
        // TODO - you fill in here.
        return null
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