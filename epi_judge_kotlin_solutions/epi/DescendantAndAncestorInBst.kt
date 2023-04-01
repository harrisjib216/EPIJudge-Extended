package epi

import epi.test_framework.BinaryTreeUtils

object DescendantAndAncestorInBst {
    fun pairIncludesAncestorAndDescendantOfM(possibleAncOrDesc0: BstNode<Integer>,
                                             possibleAncOrDesc1: BstNode<Integer>,
                                             middle: BstNode<Integer>): Boolean {
        var search0: BstNode<Integer> = possibleAncOrDesc0
        var search1: BstNode<Integer> = possibleAncOrDesc1

        // Perform interleaved searching from possibleAncOrDesc0 and
        // possibleAncOrDesc1 for middle.
        while (search0 !== possibleAncOrDesc1 && search0 !== middle && search1 !== possibleAncOrDesc0 && search1 !== middle &&
                (search0 != null || search1 != null)) {
            if (search0 != null) {
                search0 = if (search0.data > middle.data) search0.left else search0.right
            }
            if (search1 != null) {
                search1 = if (search1.data > middle.data) search1.left else search1.right
            }
        }

        // If both searches were unsuccessful, or we got from possibleAncOrDesc0
        // to possibleAncOrDesc1 without seeing middle, or from possibleAncOrDesc1
        // to possibleAncOrDesc0 without seeing middle, middle cannot lie between
        // possibleAncOrDesc0 and possibleAncOrDesc1.
        if ((search0 === possibleAncOrDesc1 || search1 === possibleAncOrDesc0 || search0 !== middle) && search1 !== middle) {
            return false
        }

        // If we get here, we already know one of possibleAncOrDesc0 or
        // possibleAncOrDesc1 has a path to middle. Check if middle has a path to
        // possibleAncOrDesc1 or to possibleAncOrDesc0.
        return if (search0 === middle) searchTarget(middle, possibleAncOrDesc1) else searchTarget(middle, possibleAncOrDesc0)
    }

    private fun searchTarget(from: BstNode<Integer>,
                             target: BstNode<Integer>): Boolean {
        var from: BstNode<Integer>? = from
        while (from != null && from !== target) {
            from = if (from.data > target.data) from.left else from.right
        }
        return from === target
    }

    @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
    @Throws(Exception::class)
    fun pairIncludesAncestorAndDescendantOfMWrapper(
            executor: TimedExecutor, tree: BstNode<Integer?>?, possibleAncOrDesc0: Int,
            possibleAncOrDesc1: Int, middle: Int): Boolean {
        val candidate0: BstNode<Integer> = BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0)
        val candidate1: BstNode<Integer> = BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1)
        val middleNode: BstNode<Integer> = BinaryTreeUtils.mustFindNode(tree, middle)
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