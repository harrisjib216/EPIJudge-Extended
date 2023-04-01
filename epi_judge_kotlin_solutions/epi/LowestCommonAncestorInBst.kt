package epi

import epi.test_framework.BinaryTreeUtils

object LowestCommonAncestorInBst {
    // Input nodes are nonempty and the key at s is less than or equal to that at
    // b.
    fun findLca(tree: BstNode<Integer>?, s: BstNode<Integer>, b: BstNode<Integer>): BstNode<Integer>? {
        var p: BstNode<Integer>? = tree
        while (p!!.data < s.data || p!!.data > b.data) {
            // Keep searching since p is outside of [s, b].
            while (p!!.data < s.data) {
                p = p!!.right // LCA must be in p's right child.
            }
            while (p!!.data > b.data) {
                p = p!!.left // LCA must be in p's left child.
            }
        }
        // Now, s.data >= p.data && p.data <= b.data.
        return p
    }

    @EpiTest(testDataFile = "lowest_common_ancestor_in_bst.tsv")
    @Throws(Exception::class)
    fun lcaWrapper(executor: TimedExecutor, tree: BstNode<Integer>?,
                   key0: Integer?, key1: Integer?): Int {
        val node0: BstNode<Integer> = BinaryTreeUtils.mustFindNode(tree, key0)
        val node1: BstNode<Integer> = BinaryTreeUtils.mustFindNode(tree, key1)
        val result: BstNode<Integer> = executor.run { findLca(tree, node0, node1) }
                ?: throw TestFailure("Result can't be null")
        return result.data
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestorInBst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}