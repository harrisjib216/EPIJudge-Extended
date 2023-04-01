package epi

import epi.test_framework.EpiTest

object TreeWithParentInorder {
    @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
    fun inorderTraversal(tree: BinaryTree<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeWithParentInorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}