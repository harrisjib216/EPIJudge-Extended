package epi

import epi.test_framework.EpiTest

object IsTreeABst {
    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    fun isBinaryTreeBST(tree: BinaryTreeNode<Integer>?): Boolean {
        return areKeysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE)
    }

    private fun areKeysInRange(tree: BinaryTreeNode<Integer>?,
                               lower: Integer, upper: Integer): Boolean {
        if (tree == null) {
            return true
        } else if (Integer.compare(tree.data, lower) < 0 ||
                Integer.compare(tree.data, upper) > 0) {
            return false
        }
        return areKeysInRange(tree.left, lower, tree.data) &&
                areKeysInRange(tree.right, tree.data, upper)
    }

    private var prev: BinaryTreeNode<Integer>? = null
    private fun isBinaryTreeBSTAlternative(tree: BinaryTreeNode<Integer>): Boolean {
        prev = null
        return inorderTraversal(tree)
    }

    private fun inorderTraversal(tree: BinaryTreeNode<Integer>?): Boolean {
        if (tree == null) {
            return true
        } else if (!inorderTraversal(tree.left)) {
            return false
        } else if (prev != null && prev.data > tree.data) {
            return false
        }
        prev = tree
        return inorderTraversal(tree.right)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeABst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}