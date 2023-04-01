package epi

import epi.test_framework.EpiTest

object IsTreeBalanced {
    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    fun isBalanced(tree: BinaryTreeNode<Integer>?): Boolean {
        return checkBalanced(tree).balanced
    }

    private fun checkBalanced(tree: BinaryTreeNode<Integer>?): BalanceStatusWithHeight {
        if (tree == null) {
            return BalanceStatusWithHeight( /*balanced=*/true,  /*height=*/-1)
        }
        val leftResult = checkBalanced(tree.left)
        if (!leftResult.balanced) {
            return leftResult
        }
        val rightResult = checkBalanced(tree.right)
        if (!rightResult.balanced) {
            return rightResult
        }
        val isBalanced: Boolean = Math.abs(leftResult.height - rightResult.height) <= 1
        val height: Int = Math.max(leftResult.height, rightResult.height) + 1
        return BalanceStatusWithHeight(isBalanced, height)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class BalanceStatusWithHeight(var balanced: Boolean, var height: Int)
}