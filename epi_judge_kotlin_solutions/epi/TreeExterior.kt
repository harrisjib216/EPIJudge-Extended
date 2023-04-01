package epi

import epi.test_framework.EpiTest

object TreeExterior {
    fun exteriorBinaryTree(tree: BinaryTreeNode<Integer>?): List<BinaryTreeNode<Integer>> {
        if (tree == null) {
            return Collections.emptyList()
        }
        val exterior: List<BinaryTreeNode<Integer>> = object : ArrayList() {
            init {
                add(tree)
            }
        }
        leftBoundary(tree.left, exterior)
        leaves(tree.left, exterior)
        leaves(tree.right, exterior)
        rightBoundary(tree.right, exterior)
        return exterior
    }

    // Computes the nodes from the root to the leftmost leaf.
    private fun leftBoundary(subtree: BinaryTreeNode<Integer>?,
                             exterior: List<BinaryTreeNode<Integer>>) {
        if (subtree == null || subtree.left == null && subtree.right == null) {
            return
        }
        exterior.add(subtree)
        if (subtree.left != null) {
            leftBoundary(subtree.left, exterior)
        } else {
            leftBoundary(subtree.right, exterior)
        }
    }

    // Computes the nodes from the rightmost leaf to the root.
    private fun rightBoundary(subtree: BinaryTreeNode<Integer>?,
                              exterior: List<BinaryTreeNode<Integer>>) {
        if ((subtree == null || subtree.left == null) && subtree.right == null) {
            return
        }
        if (subtree.right != null) {
            rightBoundary(subtree.right, exterior)
        } else {
            rightBoundary(subtree.left, exterior)
        }
        exterior.add(subtree)
    }

    // Compute the leaves in left-to-right order.
    private fun leaves(subtree: BinaryTreeNode<Integer>?,
                       exterior: List<BinaryTreeNode<Integer>>) {
        if (subtree == null) {
            return
        }
        if (subtree.left == null && subtree.right == null) {
            exterior.add(subtree)
            return
        }
        leaves(subtree.left, exterior)
        leaves(subtree.right, exterior)
    }

    @Throws(TestFailure::class)
    private fun createOutputList(L: List<BinaryTreeNode<Integer>?>): List<Integer> {
        if (L.contains(null)) {
            throw TestFailure("Resulting list contains null")
        }
        val output: List<Integer> = ArrayList()
        for (l in L) {
            output.add(l.data)
        }
        return output
    }

    @EpiTest(testDataFile = "tree_exterior.tsv")
    @Throws(Exception::class)
    fun exteriorBinaryTreeWrapper(executor: TimedExecutor,
                                  tree: BinaryTreeNode<Integer>?): List<Integer> {
        val result: List<BinaryTreeNode<Integer>> = executor.run { exteriorBinaryTree(tree) }
        return createOutputList(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeExterior.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}