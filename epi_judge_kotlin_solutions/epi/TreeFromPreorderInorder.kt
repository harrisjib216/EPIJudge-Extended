package epi

import epi.test_framework.EpiTest

object TreeFromPreorderInorder {
    @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
    fun binaryTreeFromPreorderInorder(preorder: List<Integer>, inorder: List<Integer?>): BinaryTreeNode<Integer>? {
        return binaryTreeFromPreorderInorderHelper(
                preorder,  /*preorderStart=*/0, preorder.size(),  /*inorderStart=*/0,
                inorder.size(),
                IntStream.range(0, inorder.size())
                        .boxed()
                        .collect(Collectors.toMap({ i -> inorder[i] }) { i -> i }))
    }

    // Builds the subtree with preorder.subList(preorderStart, preorderEnd) and
    // inorder.subList(inorderStart, inorderEnd).
    private fun binaryTreeFromPreorderInorderHelper(preorder: List<Integer>, preorderStart: Int,
                                                    preorderEnd: Int, inorderStart: Int,
                                                    inorderEnd: Int,
                                                    nodeToInorderIdx: Map<Integer, Integer>): BinaryTreeNode<Integer>? {
        if (preorderEnd <= preorderStart || inorderEnd <= inorderStart) {
            return null
        }
        val rootInorderIdx: Int = nodeToInorderIdx[preorder[preorderStart]]
        val leftSubtreeSize = rootInorderIdx - inorderStart
        return BinaryTreeNode(
                preorder[preorderStart],  // Recursively builds the left subtree.
                binaryTreeFromPreorderInorderHelper(
                        preorder, preorderStart + 1, preorderStart + 1 + leftSubtreeSize,
                        inorderStart, rootInorderIdx, nodeToInorderIdx),  // Recursively builds the right subtree.
                binaryTreeFromPreorderInorderHelper(
                        preorder, preorderStart + 1 + leftSubtreeSize, preorderEnd,
                        rootInorderIdx + 1, inorderEnd, nodeToInorderIdx))
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}