package epi

import epi.test_framework.EpiTest

object SearchForMinMaxInArray {
    @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")
    fun findMinMax(A: List<Integer>): MinMax {
        if (A.size() <= 1) {
            return MinMax(A[0], A[0])
        }
        var globalMinMax = MinMax.minMax(A[0], A[1])
        // Process two elements at a time.
        var i = 2
        while (i + 1 < A.size()) {
            val localMinMax = MinMax.minMax(A[i], A[i + 1])
            globalMinMax = MinMax(Math.min(globalMinMax.smallest, localMinMax.smallest),
                    Math.max(globalMinMax.largest, localMinMax.largest))
            i += 2
        }
        // If there is odd number of elements in the array, we still
        // need to compare the last element with the existing answer.
        if (A.size() % 2 !== 0) {
            globalMinMax = MinMax(Math.min(globalMinMax.smallest, A[A.size() - 1]),
                    Math.max(globalMinMax.largest, A[A.size() - 1]))
        }
        return globalMinMax
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchForMinMaxInArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Integer::class, Integer::class])
    class MinMax(smallest: Integer, largest: Integer) {
        var smallest: Integer
        var largest: Integer

        init {
            this.smallest = smallest
            this.largest = largest
        }

        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val minMax = o as MinMax
            return if (!smallest.equals(minMax.smallest)) {
                false
            } else largest.equals(minMax.largest)
        }

        @Override
        override fun toString(): String {
            return "min: $smallest, max: $largest"
        }

        companion object {
            fun minMax(a: Integer, b: Integer): MinMax {
                return if (Integer.compare(b, a) < 0) MinMax(b, a) else MinMax(a, b)
            }
        }
    }
}