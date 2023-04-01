package epi

import epi.test_framework.EpiTest

object BstMerge {
    @EpiTest(testDataFile = "bst_merge.tsv")
    fun mergeTwoBsts(A: BstNode<Integer?>?,
                     B: BstNode<Integer?>?): BstNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BstMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}