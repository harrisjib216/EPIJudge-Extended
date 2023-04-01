package epi

import epi.test_framework.EpiTest

object TreeInorder {
    @EpiTest(testDataFile = "tree_inorder.tsv")
    fun inorderTraversal(tree: BinaryTreeNode<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeInorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class NodeAndState(node: BinaryTreeNode<Integer>,
                               leftSubtreeTraversed: Boolean) {
        var node: BinaryTreeNode<Integer>
        var leftSubtreeTraversed: Boolean

        init {
            this.node = node
            this.leftSubtreeTraversed = leftSubtreeTraversed
        }
    }
}