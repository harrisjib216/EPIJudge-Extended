package epi

import epi.test_framework.EpiTest

object IntervalsUnion {
    fun unionOfIntervals(intervals: List<Interval>): List<Interval> {

        // Empty input.
        if (intervals.isEmpty()) {
            return Collections.emptyList()
        }

        // Sort intervals according to left endpoints of intervals.
        intervals.sort { a, b ->
            if (Integer.compare(a.left.`val`, b.left.`val`) !== 0) {
                return@sort a.left.`val` - b.left.`val`
            }
            // Left endpoints are equal, so now see if one is closed and the
            // other open - closed intervals should appear first.
            if (a.left.isClosed && !b.left.isClosed) {
                return@sort -1
            }
            if (!a.left.isClosed && b.left.isClosed) 1 else 0
        }
        val result: List<Interval> = ArrayList(List.of(intervals[0]))
        for (i in intervals) {
            if (!result.isEmpty() && (i.left.`val` < result[result.size() - 1].right.`val` || i.left.`val`) == result[result.size() - 1].right.`val` && (i.left.isClosed ||
                            result[result.size() - 1].right.isClosed)) {
                if (i.right.`val` > result[result.size() - 1].right.`val` || i.right.`val` == result[result.size() - 1].right.`val` && i.right.isClosed) {
                    result[result.size() - 1].right = i.right
                }
            } else {
                result.add(i)
            }
        }
        return result
    }

    @EpiTest(testDataFile = "intervals_union.tsv")
    @Throws(Exception::class)
    fun unionIntervalWrapper(executor: TimedExecutor, intervals: List<FlatInterval>): List<FlatInterval> {
        var intervals = intervals
        val casted: List<Interval> = ArrayList(intervals.size())
        for (`in` in intervals) {
            casted.add(`in`.toInterval())
        }
        val result: List<Interval> = executor.run { unionOfIntervals(casted) }
        intervals = ArrayList(result.size())
        for (i in result) {
            intervals.add(FlatInterval(i))
        }
        return intervals
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntervalsUnion.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class Interval {
        var left = Endpoint()
        var right = Endpoint()

        class Endpoint {
            var isClosed = false
            var `val` = 0
        }
    }

    @EpiUserType(ctorParams = [Int::class, Boolean::class, Int::class, Boolean::class])
    class FlatInterval {
        var leftVal = 0
        var leftIsClosed = false
        var rightVal = 0
        var rightIsClosed = false

        constructor(leftVal: Int, leftIsClosed: Boolean, rightVal: Int,
                    rightIsClosed: Boolean) {
            this.leftVal = leftVal
            this.leftIsClosed = leftIsClosed
            this.rightVal = rightVal
            this.rightIsClosed = rightIsClosed
        }

        constructor(i: Interval?) {
            if (i != null) {
                leftVal = i.left.`val`
                leftIsClosed = i.left.isClosed
                rightVal = i.right.`val`
                rightIsClosed = i.right.isClosed
            }
        }

        fun toInterval(): Interval {
            val i = Interval()
            i.left.`val` = leftVal
            i.left.isClosed = leftIsClosed
            i.right.`val` = rightVal
            i.right.isClosed = rightIsClosed
            return i
        }

        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val that = o as FlatInterval
            if (leftVal != that.leftVal) {
                return false
            }
            if (leftIsClosed != that.leftIsClosed) {
                return false
            }
            return if (rightVal != that.rightVal) {
                false
            } else rightIsClosed == that.rightIsClosed
        }

        @Override
        override fun hashCode(): Int {
            var result = leftVal
            result = 31 * result + if (leftIsClosed) 1 else 0
            result = 31 * result + rightVal
            result = 31 * result + if (rightIsClosed) 1 else 0
            return result
        }

        @Override
        override fun toString(): String {
            return "" + (if (leftIsClosed) "<" else "(") + leftVal + ", " + rightVal +
                    if (rightIsClosed) ">" else ")"
        }
    }
}