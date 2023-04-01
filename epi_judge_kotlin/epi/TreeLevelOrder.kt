package epi

import epi.test_framework.EpiTest

object TreeLevelOrder {
    @EpiTest(testDataFile = "tree_level_order.tsv")
    fun binaryTreeDepthOrder(tree: BinaryTreeNode<Integer?>?): List<List<Integer>>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeLevelOrder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}