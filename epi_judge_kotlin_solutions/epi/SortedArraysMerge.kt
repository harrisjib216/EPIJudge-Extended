package epi

import epi.test_framework.EpiTest

object SortedArraysMerge {
    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    fun mergeSortedArrays(sortedArrays: List<List<Integer?>?>): List<Integer> {
        val iters: List<Iterator<Integer>> = ArrayList(sortedArrays.size())
        for (array in sortedArrays) {
            iters.add(array.iterator())
        }
        val minHeap: PriorityQueue<ArrayEntry> = PriorityQueue(
                sortedArrays.size()) { o1, o2 -> Integer.compare(o1.value, o2.value) }
        for (i in 0 until iters.size()) {
            if (iters[i].hasNext()) {
                minHeap.add(ArrayEntry(iters[i].next(), i))
            }
        }
        val result: List<Integer> = ArrayList()
        while (!minHeap.isEmpty()) {
            val headEntry: ArrayEntry = minHeap.poll()
            result.add(headEntry.value)
            if (iters[headEntry.arrayId].hasNext()) {
                minHeap.add(ArrayEntry(iters[headEntry.arrayId].next(),
                        headEntry.arrayId))
            }
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArraysMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class ArrayEntry(value: Integer, arrayId: Integer) {
        var value: Integer
        var arrayId: Integer

        init {
            this.value = value
            this.arrayId = arrayId
        }
    }
}