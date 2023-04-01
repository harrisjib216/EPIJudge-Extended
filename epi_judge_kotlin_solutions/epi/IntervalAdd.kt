package epi

import epi.test_framework.EpiTest

object IntervalAdd {
    @EpiTest(testDataFile = "interval_add.tsv")
    fun addInterval(disjointIntervals: List<Interval>,
                    newInterval: Interval): List<Interval> {
        var newInterval = newInterval
        var i = 0
        val result: List<Interval> = ArrayList()
        // Processes intervals in disjointIntervals which come before newInterval.
        while (i < disjointIntervals.size() &&
                newInterval.left > disjointIntervals[i].right) {
            result.add(disjointIntervals[i++])
        }

        // Processes intervals in disjointIntervals which overlap with newInterval.
        while (i < disjointIntervals.size() &&
                newInterval.right >= disjointIntervals[i].left) {
            // If [a, b] and [c, d] overlap, their union is [min(a, c),max(b, d)].
            newInterval = Interval(
                    Math.min(newInterval.left, disjointIntervals[i].left),
                    Math.max(newInterval.right, disjointIntervals[i].right))
            ++i
        }
        result.add(newInterval)

        // Processes intervals in disjointIntervals which come after newInterval.
        result.addAll(disjointIntervals.subList(i, disjointIntervals.size()))
        return result
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