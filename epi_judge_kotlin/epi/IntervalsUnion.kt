package epi

import epi.test_framework.EpiTest

object IntervalsUnion {
    fun unionOfIntervals(intervals: List<Interval?>?): List<Interval> {
        // TODO - you fill in here.
        return Collections.emptyList()
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