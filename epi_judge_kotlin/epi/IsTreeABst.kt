package epi

import epi.test_framework.EpiTest

object IsTreeABst {
    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    fun isBinaryTreeBST(tree: BinaryTreeNode<Integer?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeABst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}