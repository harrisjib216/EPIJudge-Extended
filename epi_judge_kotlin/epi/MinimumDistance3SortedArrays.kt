package epi

import epi.test_framework.EpiTest

object MinimumDistance3SortedArrays {
    @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
    fun findMinDistanceSortedArrays(sortedArrays: List<List<Integer?>?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class ArrayData(var idx: Int, var `val`: Int) : Comparable<ArrayData?> {
        @Override
        operator fun compareTo(o: ArrayData): Int {
            var result: Int = Integer.compare(`val`, o.`val`)
            if (result == 0) {
                result = Integer.compare(idx, o.idx)
            }
            return result
        }
    }
}