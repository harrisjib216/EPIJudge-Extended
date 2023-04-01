package epi

import epi.test_framework.EpiTest

object SumRootToLeaf {
    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    fun sumRootToLeaf(tree: BinaryTreeNode<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SumRootToLeaf.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}