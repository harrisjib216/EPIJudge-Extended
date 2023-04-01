package epi

import epi.test_framework.EpiTest

object TreePostorder {
    @EpiTest(testDataFile = "tree_postorder.tsv")
    fun postorderTraversal(tree: BinaryTreeNode<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreePostorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class NodeAndState(node: BinaryTreeNode<Integer>,
                               subtreesTraversed: Boolean) {
        var node: BinaryTreeNode<Integer>
        var subtreesTraversed: Boolean

        init {
            this.node = node
            this.subtreesTraversed = subtreesTraversed
        }
    }
}