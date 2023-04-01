package epi

import epi.test_framework.EpiTest

object KthLargestElementInLongArray {
    fun findKthLargestUnknownLength(stream: Iterator<Integer>,
                                    k: Int): Int {
        val candidates: ArrayList<Integer> = ArrayList(2 * k - 1)
        while (stream.hasNext()) {
            val x: Int = stream.next()
            candidates.add(x)
            if (candidates.size() === 2 * k - 1) {
                // Reorders elements about k-th largest element with larger elements
                // appearing before it.
                KthLargestInArray.findKthLargest(k, candidates)
                // Resize to keep just the k largest elements seen so far.
                candidates.subList(k, candidates.size()).clear()
            }
        }
        // Finds the k-th largest element in candidates.
        return KthLargestInArray.findKthLargest(k, candidates)
    }

    @EpiTest(testDataFile = "kth_largest_element_in_long_array.tsv")
    fun findKthLargestUnknownLengthWrapper(stream: List<Integer>,
                                           k: Int): Int {
        return findKthLargestUnknownLength(stream.iterator(), k)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthLargestElementInLongArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}