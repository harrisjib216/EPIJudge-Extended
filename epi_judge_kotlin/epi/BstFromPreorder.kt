package epi

import epi.test_framework.EpiTest

object BstFromPreorder {
    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    fun rebuildBSTFromPreorder(preorderSequence: List<Integer?>?): BstNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BstFromPreorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}