package epi

import epi.test_framework.EpiTest

object KthLargestInArray {
    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
    // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
    @EpiTest(testDataFile = "kth_largest_in_array.tsv")
    fun findKthLargest(k: Int, A: List<Integer>): Int {
        return findKth(A, k, Comparator<Integer> { a, b -> Integer.compare(b, a) })
    }

    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthSmallest(1, A) returns -1, findKthSmallest(2, A) returns 1,
    // findKthSmallest(3, A) returns 2, and findKthSmallest(4, A) returns 3.
    fun findKthSmallest(k: Int, A: List<Integer>): Int {
        return findKth(A, k, Comparator<Integer> { a, b -> Integer.compare(a, b) })
    }

    fun findKth(A: List<Integer>, k: Int, cmp: Comparator<Integer>): Int {
        var left = 0
        var right: Int = A.size() - 1
        val r = Random(0)
        while (left <= right) {
            // Generates a random integer in [left, right].
            val pivotIdx: Int = r.nextInt(right - left + 1) + left
            val newPivotIdx = partitionAroundPivot(left, right, pivotIdx, A, cmp)
            if (newPivotIdx == k - 1) {
                return A[newPivotIdx]
            } else if (newPivotIdx > k - 1) {
                right = newPivotIdx - 1
            } else { // newPivotIdx < k - 1.
                left = newPivotIdx + 1
            }
        }
        throw NoSuchElementException("no k-th node in array A")
    }

    // Partitions A.subList(left, right+1) around pivotIdx, returns the new index
    // of the pivot, newPivotIdx, after partition. After partitioning,
    // A.subList(left, newPivotIdx) contains elements that are "greater than" the
    // pivot, and A.subList(newPivotIdx + 1 , right + 1) contains elements that
    // are "less than" the pivot.
    //
    // Note: "greater than" and "less than" are defined by the Comparator object.
    //
    // Returns the new index of the pivot element after partition.
    private fun partitionAroundPivot(left: Int, right: Int, pivotIdx: Int,
                                     A: List<Integer>,
                                     cmp: Comparator<Integer>): Int {
        val pivotValue: Int = A[pivotIdx]
        var newPivotIdx = left
        Collections.swap(A, pivotIdx, right)
        for (i in left until right) {
            if (cmp.compare(A[i], pivotValue) < 0) {
                Collections.swap(A, i, newPivotIdx++)
            }
        }
        Collections.swap(A, right, newPivotIdx)
        return newPivotIdx
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthLargestInArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}