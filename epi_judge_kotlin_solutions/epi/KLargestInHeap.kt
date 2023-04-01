package epi

import epi.test_framework.EpiTest

object KLargestInHeap {
    private const val DEFAULT_INITIAL_CAPACITY = 16
    @EpiTest(testDataFile = "k_largest_in_heap.tsv")
    fun kLargestInBinaryHeap(A: List<Integer?>, k: Int): List<Integer> {
        if (k <= 0) {
            return Collections.emptyList()
        }

        // Stores the (index, value)-pair in candidateMaxHeap. This heap is
        // ordered by the value field.
        val candidateMaxHeap: PriorityQueue<HeapEntry> = PriorityQueue(DEFAULT_INITIAL_CAPACITY
        ) { o1, o2 -> Integer.compare(o2.value, o1.value) }
        candidateMaxHeap.add(HeapEntry(0, A[0]))
        val result: List<Integer> = ArrayList()
        for (i in 0 until k) {
            val candidateIdx: Integer = candidateMaxHeap.peek().index
            result.add(candidateMaxHeap.remove().value)
            val leftChildIdx: Integer = 2 * candidateIdx + 1
            if (leftChildIdx < A.size()) {
                candidateMaxHeap.add(HeapEntry(leftChildIdx, A[leftChildIdx]))
            }
            val rightChildIdx: Integer = 2 * candidateIdx + 2
            if (rightChildIdx < A.size()) {
                candidateMaxHeap.add(
                        HeapEntry(rightChildIdx, A[rightChildIdx]))
            }
        }
        return result
    }

    @EpiTestComparator
    fun comp(expected: List<Integer?>, result: List<Integer?>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KLargestInHeap.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class HeapEntry(index: Integer, value: Integer?) {
        var index: Integer
        var value: Integer?

        init {
            this.index = index
            this.value = value
        }
    }
}