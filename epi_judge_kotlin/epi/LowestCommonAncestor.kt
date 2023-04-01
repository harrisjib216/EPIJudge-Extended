package epi

import epi.test_framework.BinaryTreeUtils

object LowestCommonAncestor {
    fun lca(tree: BinaryTreeNode<Integer?>?,
            node0: BinaryTreeNode<Integer?>?,
            node1: BinaryTreeNode<Integer?>?): BinaryTreeNode<Integer?>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    @Throws(Exception::class)
    fun lcaWrapper(executor: TimedExecutor,
                   tree: BinaryTreeNode<Integer?>?, key0: Integer?,
                   key1: Integer?): Int {
        val node0: BinaryTreeNode<Integer?> = BinaryTreeUtils.mustFindNode(tree, key0)
        val node1: BinaryTreeNode<Integer?> = BinaryTreeUtils.mustFindNode(tree, key1)
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
}