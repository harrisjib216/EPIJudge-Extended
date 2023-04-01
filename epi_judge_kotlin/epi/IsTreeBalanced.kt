package epi

import epi.test_framework.EpiTest

object IsTreeBalanced {
    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    fun isBalanced(tree: BinaryTreeNode<Integer?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}