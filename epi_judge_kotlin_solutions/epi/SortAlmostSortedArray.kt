package epi

import epi.test_framework.EpiTest

object SortAlmostSortedArray {
    fun sortApproximatelySortedData(sequence: Iterator<Integer?>, k: Int): List<Integer> {
        val minHeap: PriorityQueue<Integer> = PriorityQueue()
        // Adds the first k elements into minHeap. Stop if there are fewer than k
        // elements.
        var i = 0
        while (i < k && sequence.hasNext()) {
            minHeap.add(sequence.next())
            ++i
        }
        val result: List<Integer> = ArrayList()
        // For every new element, add it to minHeap and extract the smallest.
        while (sequence.hasNext()) {
            minHeap.add(sequence.next())
            val smallest: Integer = minHeap.remove()
            result.add(smallest)
        }

        // sequence is exhausted, iteratively extracts the remaining elements.
        result.addAll(Stream.generate(minHeap::remove)
                .limit(minHeap.size())
                .collect(Collectors.toList()))
        return result
    }

    @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
    fun sortApproximatelySortedDataWrapper(sequence: List<Integer?>, k: Int): List<Integer> {
        return sortApproximatelySortedData(sequence.iterator(), k)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}