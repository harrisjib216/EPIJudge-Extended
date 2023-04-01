package epi

import epi.test_framework.EpiTest

object TreeExterior {
    fun exteriorBinaryTree(tree: BinaryTreeNode<Integer?>?): List<BinaryTreeNode<Integer?>> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    @Throws(TestFailure::class)
    private fun createOutputList(L: List<BinaryTreeNode<Integer>?>): List<Integer> {
        if (L.contains(null)) {
            throw TestFailure("Resulting list contains null")
        }
        val output: List<Integer> = ArrayList()
        for (l in L) {
            output.add(l.data)
        }
        return output
    }

    @EpiTest(testDataFile = "tree_exterior.tsv")
    @Throws(Exception::class)
    fun exteriorBinaryTreeWrapper(executor: TimedExecutor,
                                  tree: BinaryTreeNode<Integer?>?): List<Integer> {
        val result: List<BinaryTreeNode<Integer>> = executor.run { exteriorBinaryTree(tree) }
        return createOutputList(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeExterior.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}