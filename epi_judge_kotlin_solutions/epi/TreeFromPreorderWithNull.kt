package epi

import epi.test_framework.EpiTest

object TreeFromPreorderWithNull {
    // Global variable, tracks current subtree.
    private var subtreeIdx: Integer? = null
    fun reconstructPreorder(preorder: List<Integer>): BinaryTreeNode<Integer>? {
        subtreeIdx = 0
        return reconstructPreorderSubtree(preorder)
    }

    // Reconstructs the subtree that is rooted at subtreeIdx.
    private fun reconstructPreorderSubtree(preorder: List<Integer>): BinaryTreeNode<Integer>? {
        val subtreeKey: Integer = preorder[subtreeIdx]
        ++subtreeIdx
        if (subtreeKey == null) {
            return null
        }
        // Note that reconstructPreorderSubtree updates subtreeIdx. So the order of
        // following two calls are critical.
        val leftSubtree: BinaryTreeNode<Integer>? = reconstructPreorderSubtree(preorder)
        val rightSubtree: BinaryTreeNode<Integer>? = reconstructPreorderSubtree(preorder)
        return BinaryTreeNode(subtreeKey, leftSubtree, rightSubtree)
    }

    @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
    @Throws(Exception::class)
    fun reconstructPreorderWrapper(executor: TimedExecutor, strings: List<String>): BinaryTreeNode<Integer> {
        val ints: List<Integer> = ArrayList()
        for (s in strings) {
            if (s.equals("null")) {
                ints.add(null)
            } else {
                ints.add(Integer.parseInt(s))
            }
        }
        return executor.run { reconstructPreorder(ints) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}