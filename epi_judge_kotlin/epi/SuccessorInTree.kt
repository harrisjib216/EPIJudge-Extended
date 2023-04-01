package epi

import epi.test_framework.BinaryTreeUtils

object SuccessorInTree {
    fun findSuccessor(node: BinaryTree<Integer?>?): BinaryTree<Integer?>? {
        // TODO - you fill in here.
        return null
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