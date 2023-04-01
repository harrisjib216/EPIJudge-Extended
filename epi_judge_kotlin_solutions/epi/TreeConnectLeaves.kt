package epi

import epi.test_framework.EpiTest

object TreeConnectLeaves {
    fun createListOfLeaves(tree: BinaryTreeNode<Integer>?): List<BinaryTreeNode<Integer>> {
        val leaves: List<BinaryTreeNode<Integer>> = ArrayList()
        addLeavesLeftToRight(tree, leaves)
        return leaves
    }

    private fun addLeavesLeftToRight(tree: BinaryTreeNode<Integer>?,
                                     leaves: List<BinaryTreeNode<Integer>>) {
        if (tree != null) {
            if (tree.left == null && tree.right == null) {
                leaves.add(tree)
            } else {
                addLeavesLeftToRight(tree.left, leaves)
                addLeavesLeftToRight(tree.right, leaves)
            }
        }
    }

    @EpiTest(testDataFile = "tree_connect_leaves.tsv")
    @Throws(Exception::class)
    fun createListOfLeavesWrapper(executor: TimedExecutor,
                                  tree: BinaryTreeNode<Integer>?): List<Integer> {
        val result: List<BinaryTreeNode<Integer>> = executor.run { createListOfLeaves(tree) }
        if (result.stream().anyMatch { x -> x == null || x.data == null }) {
            throw TestFailure("Result can't contain null")
        }
        val extractedRes: List<Integer> = ArrayList()
        for (x in result) {
            extractedRes.add(x.data)
        }
        return extractedRes
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeConnectLeaves.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}