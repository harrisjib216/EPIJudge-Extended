package epi

import epi.test_framework.BinaryTreeUtils

object LowestCommonAncestorCloseAncestor {
    fun lca(node0: BinaryTree<Integer?>?,
            node1: BinaryTree<Integer?>?): BinaryTree<Integer?> {
        var node0: BinaryTree<Integer?>? = node0
        var node1: BinaryTree<Integer?>? = node1
        val hash: Set<BinaryTree<Integer>> = HashSet()
        while (node0 != null || node1 != null) {
            // Ascend tree in tandem from these two nodes.
            if (node0 != null) {
                if (!hash.add(node0)) {
                    return node0
                }
                node0 = node0.parent
            }
            if (node1 != null) {
                if (!hash.add(node1)) {
                    return node1
                }
                node1 = node1.parent
            }
        }
        throw IllegalArgumentException(
                "node0 and node1 are not in the same tree")
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