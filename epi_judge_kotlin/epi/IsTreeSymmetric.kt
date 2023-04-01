package epi

import epi.test_framework.EpiTest

object IsTreeSymmetric {
    @EpiTest(testDataFile = "is_tree_symmetric.tsv")
    fun isSymmetric(tree: BinaryTreeNode<Integer?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeSymmetric.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}