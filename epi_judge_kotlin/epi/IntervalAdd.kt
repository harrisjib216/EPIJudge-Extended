package epi

import epi.test_framework.EpiTest

object IntervalAdd {
    @EpiTest(testDataFile = "interval_add.tsv")
    fun addInterval(disjointIntervals: List<Interval?>?,
                    newInterval: Interval?): List<Interval>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntervalAdd.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Interval(var left: Int, var right: Int) {
        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val interval = o as Interval
            return if (left != interval.left) {
                false
            } else right == interval.right
        }

        @Override
        override fun toString(): String {
            return "[$left, $right]"
        }
    }
}