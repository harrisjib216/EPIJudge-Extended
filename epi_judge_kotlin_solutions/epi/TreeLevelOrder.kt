package epi

import epi.test_framework.EpiTest

object TreeLevelOrder {
    @EpiTest(testDataFile = "tree_level_order.tsv")
    fun binaryTreeDepthOrder(tree: BinaryTreeNode<Integer?>?): List<List<Integer>> {
        val result: List<List<Integer>> = ArrayList()
        if (tree == null) {
            return result
        }
        var currDepthNodes: List<BinaryTreeNode<Integer?>?> = List.of(tree)
        while (!currDepthNodes.isEmpty()) {
            result.add(currDepthNodes.stream()
                    .map { curr -> curr.data }
                    .collect(Collectors.toList()))
            currDepthNodes = currDepthNodes.stream()
                    .map { curr -> Arrays.asList(curr.left, curr.right) }
                    .flatMap(List::stream)
                    .filter { child -> child != null }
                    .collect(Collectors.toList())
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeLevelOrder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}