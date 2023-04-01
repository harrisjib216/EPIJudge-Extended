package epi

import epi.test_framework.EpiTest

object TreeFromPreorderInorder {
    @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
    fun binaryTreeFromPreorderInorder(preorder: List<Integer?>?, inorder: List<Integer?>?): BinaryTreeNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}