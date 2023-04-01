package epi

import epi.test_framework.EpiTest

object TreePreorder {
    @EpiTest(testDataFile = "tree_preorder.tsv")
    fun preorderTraversal(tree: BinaryTreeNode<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreePreorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class NodeAndState(node: BinaryTreeNode<Integer>, nodeProcessed: Boolean) {
        var node: BinaryTreeNode<Integer>
        var nodeProcessed: Boolean

        init {
            this.node = node
            this.nodeProcessed = nodeProcessed
        }
    }
}