package epi

import epi.test_framework.EpiTest

object EnumerateTrees {
    fun generateAllBinaryTrees(numNodes: Int): List<BinaryTreeNode<Integer?>> {
        // TODO - you fill in here.
        return Collections.emptyList()
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