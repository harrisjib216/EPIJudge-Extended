package epi

import epi.test_framework.EpiTest

object PathSum {
    @EpiTest(testDataFile = "path_sum.tsv")
    fun hasPathSum(tree: BinaryTreeNode<Integer>?,
                   remainingWeight: Int): Boolean {
        if (tree == null) {
            return false
        } else if (tree.left == null && tree.right == null) { // Leaf.
            return remainingWeight == tree.data
        }
        // Non-leaf.
        return hasPathSum(tree.left, remainingWeight - tree.data) ||
                hasPathSum(tree.right, remainingWeight - tree.data)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PathSum.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}