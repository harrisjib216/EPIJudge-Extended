package epi

import epi.test_framework.EpiTest

object KthNodeInTree {
    fun findKthNodeBinaryTree(tree: BinaryTreeNode<Integer?>?, k: Int): BinaryTreeNode<Integer?>? {
        var k = k
        var iter: BinaryTreeNode<Integer?>? = tree
        while (iter != null) {
            val leftSize = if (iter.left != null) iter.left.size else 0
            if (leftSize + 1 < k) { // k-th node must be in right subtree of iter.
                k -= leftSize + 1
                iter = iter.right
            } else if (leftSize == k - 1) { // k-th is iter itself.
                return iter
            } else { // k-th node must be in left subtree of iter.
                iter = iter.left
            }
        }
        // If k is between 1 and the tree size, this line is unreachable.
        return null
    }

    fun convertToTreeWithSize(original: BinaryTree<Integer?>?): BinaryTreeNode<Integer?>? {
        if (original == null) return null
        val left: BinaryTreeNode<Integer?>? = convertToTreeWithSize(original.left)
        val right: BinaryTreeNode<Integer?>? = convertToTreeWithSize(original.right)
        val lSize = left?.size ?: 0
        val rSize = right?.size ?: 0
        return BinaryTreeNode<T?>(original.data, left!!, right!!, 1 + lSize + rSize)
    }

    @EpiTest(testDataFile = "kth_node_in_tree.tsv")
    @Throws(Exception::class)
    fun findKthNodeBinaryTreeWrapper(executor: TimedExecutor,
                                     tree: BinaryTree<Integer?>?,
                                     k: Int): Int {
        val converted: BinaryTreeNode<Integer?>? = convertToTreeWithSize(tree)
        val result: BinaryTreeNode<Integer> = executor.run { findKthNodeBinaryTree(converted, k) }
                ?: throw TestFailure("Result can't be null")
        return result.data
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthNodeInTree.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class BinaryTreeNode<T>(@get:Override override var data: T, @get:Override override var left: BinaryTreeNode<T>,
                            @get:Override override var right: BinaryTreeNode<T>, var size: Int) : TreeLike<T, BinaryTreeNode<T>?>()
}