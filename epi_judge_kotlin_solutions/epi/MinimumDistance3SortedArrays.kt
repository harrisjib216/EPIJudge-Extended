package epi

import epi.test_framework.EpiTest

object MinimumDistance3SortedArrays {
    @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
    fun findMinDistanceSortedArrays(sortedArrays: List<List<Integer>>): Int {

        // Indices into each of the arrays.
        val heads: List<Integer> = ArrayList(sortedArrays.size())
        for (arr in sortedArrays) {
            heads.add(0)
        }
        val currentHeads: NavigableSet<ArrayData> = TreeSet()

        // Adds the minimum element of each array in to currentHeads.
        for (i in 0 until sortedArrays.size()) {
            currentHeads.add(ArrayData(i, sortedArrays[i][heads[i]]))
        }
        var minDistanceSoFar: Int = Integer.MAX_VALUE
        while (true) {
            minDistanceSoFar = Math.min(
                    minDistanceSoFar, currentHeads.last().`val` - currentHeads.first().`val`)
            val idxNextMin: Int = currentHeads.first().idx
            // Return if some array has no remaining elements.
            heads.set(idxNextMin, heads[idxNextMin] + 1)
            if (heads[idxNextMin] >= sortedArrays[idxNextMin].size()) {
                return minDistanceSoFar
            }
            currentHeads.pollFirst()
            currentHeads.add(ArrayData(
                    idxNextMin, sortedArrays[idxNextMin][heads[idxNextMin]]))
        }
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