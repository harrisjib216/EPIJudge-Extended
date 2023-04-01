package epi

import epi.test_framework.EpiTest

object EnumerateTrees {
    fun generateAllBinaryTrees(numNodes: Int): List<BinaryTreeNode<Integer>> {
        val result: List<BinaryTreeNode<Integer>> = ArrayList()
        if (numNodes == 0) { // Empty tree, add as an null.
            result.add(null)
        }
        for (numLeftTreeNodes in 0 until numNodes) {
            val numRightTreeNodes: Int = numNodes - 1 - numLeftTreeNodes
            val leftSubtrees: List<BinaryTreeNode<Integer>> = generateAllBinaryTrees(numLeftTreeNodes)
            val rightSubtrees: List<BinaryTreeNode<Integer>> = generateAllBinaryTrees(numNodes - 1 - numLeftTreeNodes)
            // Generates all combinations of leftSubtrees and rightSubtrees.
            for (left in leftSubtrees) {
                for (right in rightSubtrees) {
                    result.add(BinaryTreeNode(0, left, right))
                }
            }
        }
        return result
    }

    fun serializeStructure(tree: BinaryTreeNode<Integer?>?): List<Integer> {
        val result: List<Integer> = ArrayList()
        val stack: Stack<BinaryTreeNode<Integer>> = Stack()
        stack.push(tree)
        while (!stack.empty()) {
            val p: BinaryTreeNode<Integer> = stack.pop()
            result.add(if (p == null) 0 else 1)
            if (p != null) {
                stack.push(p.left)
                stack.push(p.right)
            }
        }
        return result
    }

    @EpiTest(testDataFile = "enumerate_trees.tsv")
    @Throws(Exception::class)
    fun generateAllBinaryTreesWrapper(executor: TimedExecutor, numNodes: Int): List<List<Integer>> {
        val result: List<BinaryTreeNode<Integer>> = executor.run { generateAllBinaryTrees(numNodes) }
        val serialized: List<List<Integer>> = ArrayList()
        for (x in result) {
            serialized.add(serializeStructure(x))
        }
        serialized.sort(LexicographicalListComparator())
        return serialized
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EnumerateTrees.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}