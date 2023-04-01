package epi

import epi.test_framework.EpiTest

object RectangleIntersection {
    @EpiTest(testDataFile = "rectangle_intersection.tsv")
    fun intersectRectangle(r1: Rect?, r2: Rect?): Rect {
        // TODO - you fill in here.
        return Rect(0, 0, 0, 0)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RectangleIntersection.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class, Int::class, Int::class])
    class Rect(var x: Int, var y: Int, var width: Int, var height: Int) {
        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val rectangle = o as Rect
            if (x != rectangle.x) {
                return false
            }
            if (y != rectangle.y) {
                return false
            }
            return if (width != rectangle.width) {
                false
            } else height == rectangle.height
        }

        @Override
        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            result = 31 * result + width
            result = 31 * result + height
            return result
        }

        @Override
        override fun toString(): String {
            return "[$x, $y, $width, $height]"
        }
    }
}