package epi

import epi.test_framework.EpiTest

object SumRootToLeaf {
    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    fun sumRootToLeaf(tree: BinaryTreeNode<Integer>?): Int {
        return sumRootToLeafHelper(tree,  /*partialPathSum=*/0)
    }

    private fun sumRootToLeafHelper(tree: BinaryTreeNode<Integer>?,
                                    partialPathSum: Int): Int {
        var partialPathSum = partialPathSum
        if (tree == null) {
            return 0
        }
        partialPathSum = partialPathSum * 2 + tree.data
        return if (tree.left == null && tree.right == null) { // Leaf.
            partialPathSum
        } else sumRootToLeafHelper(tree.left, partialPathSum) +
                sumRootToLeafHelper(tree.right, partialPathSum)
        // Non-leaf.
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SumRootToLeaf.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}