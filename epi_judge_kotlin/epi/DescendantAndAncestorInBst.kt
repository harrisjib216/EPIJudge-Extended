package epi

import epi.test_framework.BinaryTreeUtils

object DescendantAndAncestorInBst {
    fun pairIncludesAncestorAndDescendantOfM(possibleAncOrDesc0: BstNode<Integer?>?,
                                             possibleAncOrDesc1: BstNode<Integer?>?,
                                             middle: BstNode<Integer?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
    @Throws(Exception::class)
    fun pairIncludesAncestorAndDescendantOfMWrapper(
            executor: TimedExecutor, tree: BstNode<Integer?>?, possibleAncOrDesc0: Int,
            possibleAncOrDesc1: Int, middle: Int): Boolean {
        val candidate0: BstNode<Integer?> = BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0)
        val candidate1: BstNode<Integer?> = BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1)
        val middleNode: BstNode<Integer?> = BinaryTreeUtils.mustFindNode(tree, middle)
        return executor.run {
            pairIncludesAncestorAndDescendantOfM(
                    candidate0, candidate1, middleNode)
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DescendantAndAncestorInBst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}