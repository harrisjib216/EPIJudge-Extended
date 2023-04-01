package epi

import epi.test_framework.BinaryTreeUtils

object BstFromSortedArray {
    fun buildMinHeightBSTFromSortedArray(A: List<Integer>): BstNode<Integer>? {
        return buildMinHeightBSTFromSortedSubarray(A, 0, A.size())
    }

    // Build a min-height BST over the entries in A.subList(start, end - 1).
    private fun buildMinHeightBSTFromSortedSubarray(A: List<Integer>, start: Int, end: Int): BstNode<Integer>? {
        if (start >= end) {
            return null
        }
        val mid = start + (end - start) / 2
        return BstNode(A[mid],
                buildMinHeightBSTFromSortedSubarray(A, start, mid),
                buildMinHeightBSTFromSortedSubarray(A, mid + 1, end))
    }

    @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
    @Throws(Exception::class)
    fun buildMinHeightBSTFromSortedArrayWrapper(executor: TimedExecutor,
                                                A: List<Integer>): Int {
        val result: BstNode<Integer> = executor.run { buildMinHeightBSTFromSortedArray(A) }
        val inorder: List<Integer> = BinaryTreeUtils.generateInorder(result)
        TestUtils.assertAllValuesPresent(A, inorder)
        BinaryTreeUtils.assertTreeIsBst(result)
        return BinaryTreeUtils.binaryTreeHeight(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BstFromSortedArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}