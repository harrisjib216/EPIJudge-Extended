package epi

import epi.test_framework.EpiTest

object TreePreorder {
    @EpiTest(testDataFile = "tree_preorder.tsv")
    fun preorderTraversal(tree: BinaryTreeNode<Integer?>?): List<Integer> {
        val result: List<Integer> = ArrayList()
        val inProcess: Deque<NodeAndState> = ArrayDeque()
        inProcess.addFirst(NodeAndState(tree, false))
        while (!inProcess.isEmpty()) {
            val nodeAndState: NodeAndState = inProcess.removeFirst()
            if (nodeAndState.node != null) {
                if (nodeAndState.nodeProcessed) {
                    result.add(nodeAndState.node.data)
                } else {
                    inProcess.addFirst(NodeAndState(nodeAndState.node.right, false))
                    inProcess.addFirst(NodeAndState(nodeAndState.node.left, false))
                    inProcess.addFirst(NodeAndState(nodeAndState.node, true))
                }
            }
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreePreorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class NodeAndState(node: BinaryTreeNode<Integer?>?, nodeProcessed: Boolean) {
        var node: BinaryTreeNode<Integer?>?
        var nodeProcessed: Boolean

        init {
            this.node = node
            this.nodeProcessed = nodeProcessed
        }
    }
}