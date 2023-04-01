package epi

import epi.test_framework.EpiTest

object IsTreeSymmetric {
    @EpiTest(testDataFile = "is_tree_symmetric.tsv")
    fun isSymmetric(tree: BinaryTreeNode<Integer>?): Boolean {
        return tree == null || checkSymmetric(tree.left, tree.right)
    }

    private fun checkSymmetric(subtree0: BinaryTreeNode<Integer>?,
                               subtree1: BinaryTreeNode<Integer>?): Boolean {
        if (subtree0 == null && subtree1 == null) {
            return true
        } else if (subtree0 != null && subtree1 != null) {
            return subtree0.data === subtree1.data &&
                    checkSymmetric(subtree0.left, subtree1.right) &&
                    checkSymmetric(subtree0.right, subtree1.left)
        }
        // One subtree is empty, and the other is not.
        return false
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeSymmetric.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}