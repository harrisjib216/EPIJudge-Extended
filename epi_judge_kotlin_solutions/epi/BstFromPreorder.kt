package epi

import epi.test_framework.EpiTest

object BstFromPreorder {
    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    fun rebuildBSTFromPreorder(preorderSequence: List<Integer>): BstNode<Integer>? {
        return rebuildBSTFromPreorderHelper(preorderSequence, 0,
                preorderSequence.size())
    }

    // Builds a BST from preorderSequence.subList(start, end).
    private fun rebuildBSTFromPreorderHelper(preorderSequence: List<Integer>, start: Int,
                                             end: Int): BstNode<Integer>? {
        if (start >= end) {
            return null
        }
        var transitionPoint = start + 1
        while (transitionPoint < end &&
                Integer.compare(preorderSequence[transitionPoint],
                        preorderSequence[start]) < 0) {
            ++transitionPoint
        }
        return BstNode(
                preorderSequence[start],
                rebuildBSTFromPreorderHelper(preorderSequence, start + 1,
                        transitionPoint),
                rebuildBSTFromPreorderHelper(preorderSequence, transitionPoint, end))
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BstFromPreorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}