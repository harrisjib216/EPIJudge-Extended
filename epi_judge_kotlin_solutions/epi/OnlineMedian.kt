package epi

import epi.test_framework.EpiTest

object OnlineMedian {
    private const val DEFAULT_INITIAL_CAPACITY = 16
    fun onlineMedian(sequence: Iterator<Integer>): List<Double> {

        // minHeap stores the larger half seen so far.
        val minHeap: PriorityQueue<Integer> = PriorityQueue()
        // maxHeap stores the smaller half seen so far.
        val maxHeap: PriorityQueue<Integer> = PriorityQueue(
                DEFAULT_INITIAL_CAPACITY, Collections.reverseOrder())
        val result: List<Double> = ArrayList()
        while (sequence.hasNext()) {
            val x: Int = sequence.next()
            minHeap.add(x)
            maxHeap.add(minHeap.remove())
            // Ensure minHeap and maxHeap have equal number of elements if
            // an even number of elements is read; otherwise, minHeap must have
            // one more element than maxHeap.
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.remove())
            }
            result.add(if (minHeap.size() === maxHeap.size()) 0.5 * (minHeap.peek() + maxHeap.peek()) else minHeap.peek())
        }
        return result
    }

    @EpiTest(testDataFile = "online_median.tsv")
    fun onlineMedianWrapper(sequence: List<Integer>): List<Double> {
        return onlineMedian(sequence.iterator())
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "OnlineMedian.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}