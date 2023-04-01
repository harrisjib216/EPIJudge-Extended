package epi

import epi.test_framework.EpiTest

object TreeInorder {
    @EpiTest(testDataFile = "tree_inorder.tsv")
    fun inorderTraversal(tree: BinaryTreeNode<Integer?>?): List<Integer> {
        val result: List<Integer> = ArrayList()
        val inProcess: Deque<NodeAndState> = ArrayDeque()
        inProcess.addFirst(NodeAndState(tree,  /*leftSubtreeTraversed=*/false))
        while (!inProcess.isEmpty()) {
            val nodeAndState: NodeAndState = inProcess.removeFirst()
            if (nodeAndState.node != null) {
                if (nodeAndState.leftSubtreeTraversed) {
                    result.add(nodeAndState.node.data)
                } else {
                    inProcess.addFirst(NodeAndState(nodeAndState.node.right,  /*leftSubtreeTraversed=*/
                            false))
                    inProcess.addFirst(NodeAndState(nodeAndState.node,  /*leftSubtreeTraversed=*/
                            true))
                    inProcess.addFirst(NodeAndState(nodeAndState.node.left,  /*leftSubtreeTraversed=*/
                            false))
                }
            }
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeInorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class NodeAndState(node: BinaryTreeNode<Integer?>?,
                               leftSubtreeTraversed: Boolean) {
        var node: BinaryTreeNode<Integer?>?
        var leftSubtreeTraversed: Boolean

        init {
            this.node = node
            this.leftSubtreeTraversed = leftSubtreeTraversed
        }
    }
}