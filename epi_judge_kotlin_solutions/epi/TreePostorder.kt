package epi

import epi.test_framework.EpiTest

object TreePostorder {
    @EpiTest(testDataFile = "tree_postorder.tsv")
    fun postorderTraversal(tree: BinaryTreeNode<Integer?>?): List<Integer> {
        val result: List<Integer> = ArrayList()
        val inProcess: Deque<NodeAndState> = ArrayDeque()
        inProcess.addFirst(NodeAndState(tree, false))
        while (!inProcess.isEmpty()) {
            val nodeAndState: NodeAndState = inProcess.removeFirst()
            if (nodeAndState.node != null) {
                if (nodeAndState.subtreesTraversed) {
                    result.add(nodeAndState.node.data)
                } else {
                    inProcess.addFirst(NodeAndState(nodeAndState.node, true))
                    inProcess.addFirst(NodeAndState(nodeAndState.node.right, false))
                    inProcess.addFirst(NodeAndState(nodeAndState.node.left, false))
                }
            }
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreePostorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class NodeAndState(node: BinaryTreeNode<Integer?>?,
                               subtreesTraversed: Boolean) {
        var node: BinaryTreeNode<Integer?>?
        var subtreesTraversed: Boolean

        init {
            this.node = node
            this.subtreesTraversed = subtreesTraversed
        }
    }
}