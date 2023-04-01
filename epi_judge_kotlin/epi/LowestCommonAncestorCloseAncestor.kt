package epi

import epi.test_framework.BinaryTreeUtils

object LowestCommonAncestorCloseAncestor {
    fun lca(node0: BinaryTree<Integer?>?,
            node1: BinaryTree<Integer?>?): BinaryTree<Integer?>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    @Throws(Exception::class)
    fun lcaWrapper(executor: TimedExecutor, tree: BinaryTree<Integer?>?,
                   key0: Integer?, key1: Integer?): Int {
        val node0: BinaryTree<Integer?> = BinaryTreeUtils.mustFindNode(tree, key0)
        val node1: BinaryTree<Integer?> = BinaryTreeUtils.mustFindNode(tree, key1)
        val result: BinaryTree<Integer> = executor.run { lca(node0, node1) }
                ?: throw TestFailure("Result can not be null")
        return result.data
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestorCloseAncestor.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}