package epi

import epi.test_framework.BinaryTreeUtils

object LowestCommonAncestor {
    fun lca(tree: BinaryTreeNode<Integer>?,
            node0: BinaryTreeNode<Integer>,
            node1: BinaryTreeNode<Integer>): BinaryTreeNode<Integer>? {
        return lcaHelper(tree, node0, node1).ancestor
    }

    // Returns an object consisting of an int and a node. The int field is
    // 0, 1, or 2 depending on how many of {node0, node1} are present in
    // the tree. If both are present in the tree, when ancestor is
    // assigned to a non-null value, it is the LCA.
    private fun lcaHelper(tree: BinaryTreeNode<Integer>?,
                          node0: BinaryTreeNode<Integer>,
                          node1: BinaryTreeNode<Integer>): Status {
        if (tree == null) {
            return Status( /*numTargetNodes=*/0,  /*ancestor=*/null)
        }
        val leftResult = lcaHelper(tree.left, node0, node1)
        if (leftResult.numTargetNodes == 2) {
            // Found both nodes in the left subtree.
            return leftResult
        }
        val rightResult = lcaHelper(tree.right, node0, node1)
        if (rightResult.numTargetNodes == 2) {
            // Found both nodes in the right subtree.
            return rightResult
        }
        val numTargetNodes = leftResult.numTargetNodes +
                rightResult.numTargetNodes + (if (tree === node0) 1 else 0) +
                if (tree === node1) 1 else 0
        return Status(numTargetNodes, if (numTargetNodes == 2) tree else null)
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    @Throws(Exception::class)
    fun lcaWrapper(executor: TimedExecutor,
                   tree: BinaryTreeNode<Integer>?, key0: Integer?,
                   key1: Integer?): Int {
        val node0: BinaryTreeNode<Integer> = BinaryTreeUtils.mustFindNode(tree, key0)
        val node1: BinaryTreeNode<Integer> = BinaryTreeUtils.mustFindNode(tree, key1)
        val result: BinaryTreeNode<Integer> = executor.run { lca(tree, node0, node1) }
                ?: throw TestFailure("Result can not be null")
        return result.data
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestor.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class Status(var numTargetNodes: Int, node: BinaryTreeNode<Integer>?) {
        var ancestor: BinaryTreeNode<Integer>?

        init {
            ancestor = node
        }
    }
}