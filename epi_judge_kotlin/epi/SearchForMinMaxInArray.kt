package epi

import epi.test_framework.EpiTest

object SearchForMinMaxInArray {
    @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")
    fun findMinMax(A: List<Integer?>?): MinMax {
        // TODO - you fill in here.
        return MinMax(0, 0)
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
            private fun minMax(a: Integer, b: Integer): MinMax {
                return if (Integer.compare(b, a) < 0) MinMax(b, a) else MinMax(a, b)
            }
        }
    }
}