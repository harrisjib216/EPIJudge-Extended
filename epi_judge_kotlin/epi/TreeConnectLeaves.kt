package epi

import epi.test_framework.EpiTest

object TreeConnectLeaves {
    fun createListOfLeaves(tree: BinaryTreeNode<Integer?>?): List<BinaryTreeNode<Integer?>> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    @EpiTest(testDataFile = "tree_connect_leaves.tsv")
    @Throws(Exception::class)
    fun createListOfLeavesWrapper(executor: TimedExecutor,
                                  tree: BinaryTreeNode<Integer?>?): List<Integer> {
        val result: List<BinaryTreeNode<Integer>> = executor.run { createListOfLeaves(tree) }
        if (result.stream().anyMatch { x -> x == null || x.data == null }) {
            throw TestFailure("Result can't contain null")
        }
        val extractedRes: List<Integer> = ArrayList()
        for (x in result) {
            extractedRes.add(x.data)
        }
        return extractedRes
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeConnectLeaves.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}