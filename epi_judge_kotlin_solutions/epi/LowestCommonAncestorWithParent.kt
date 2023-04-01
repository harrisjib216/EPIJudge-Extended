package epi

import epi.test_framework.BinaryTreeUtils

object LowestCommonAncestorWithParent {
    fun lca(node0: BinaryTree<Integer?>?,
            node1: BinaryTree<Integer?>?): BinaryTree<Integer?>? {
        var node0: BinaryTree<Integer?>? = node0
        var node1: BinaryTree<Integer?>? = node1
        val depth0 = getDepth(node0)
        val depth1 = getDepth(node1)
        // Makes node0 as the deeper node in order to simplify the code.
        if (depth1 > depth0) {
            val temp: BinaryTree<Integer?>? = node0
            node0 = node1
            node1 = temp
        }
        // Ascends from the deeper node.
        var depthDiff: Int = Math.abs(depth0 - depth1)
        while (depthDiff-- > 0) {
            node0 = node0!!.parent
        }

        // Now ascends both nodes until we reach the LCA.
        while (node0 !== node1) {
            node0 = node0!!.parent
            node1 = node1!!.parent
        }
        return node0
    }

    private fun getDepth(node: BinaryTree<Integer?>?): Int {
        var node: BinaryTree<Integer?>? = node
        var depth = 0
        while (node!!.parent != null) {
            ++depth
            node = node!!.parent
        }
        return depth
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
                        .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}