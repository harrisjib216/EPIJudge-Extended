package epi

import epi.test_framework.EpiTest

object PathSum {
    @EpiTest(testDataFile = "path_sum.tsv")
    fun hasPathSum(tree: BinaryTreeNode<Integer?>?,
                   remainingWeight: Int): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PathSum.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}