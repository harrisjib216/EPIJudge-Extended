package epi

import epi.test_framework.BinaryTreeUtils

object SuccessorInTree {
    fun findSuccessor(node: BinaryTree<Integer?>?): BinaryTree<Integer?>? {
        var iter: BinaryTree<Integer?>? = node
        if (iter!!.right != null) {
            // Find the leftmost element in node's right subtree.
            iter = iter!!.right
            while (iter!!.left != null) {
                iter = iter!!.left
            }
            return iter
        }

        // Find the closest ancestor whose left subtree contains node.
        while (iter!!.parent != null && iter!!.parent.right === iter) {
            iter = iter!!.parent
        }
        // A return value of null means node does not have successor, i.e., it is
        // the rightmost node in the tree.
        return iter!!.parent
    }

    @EpiTest(testDataFile = "successor_in_tree.tsv")
    @Throws(Exception::class)
    fun findSuccessorWrapper(executor: TimedExecutor,
                             tree: BinaryTree<Integer?>?, nodeIdx: Int): Int {
        val n: BinaryTree<Integer?> = BinaryTreeUtils.mustFindNode(tree, nodeIdx)
        val result: BinaryTree<Integer> = executor.run { findSuccessor(n) }
        return if (result == null) -1 else result.data
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SuccessorInTree.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}