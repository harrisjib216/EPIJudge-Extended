package epi

import epi.test_framework.EpiTest

object TreeRightSibling {
    fun constructRightSibling(tree: BinaryTreeNode<Integer?>?) {
        var tree: BinaryTreeNode<Integer?>? = tree
        while (tree != null) {
            populateLowerLevelNextField(tree)
            tree = tree.left
        }
    }

    private fun populateLowerLevelNextField(startNode: BinaryTreeNode<Integer?>) {
        var startNode: BinaryTreeNode<Integer?>? = startNode
        while (startNode != null && startNode.left != null) {
            // Populate left child's next field.
            startNode.left!!.next = startNode.right
            // Populate right child's next field if startNode is not the last node
            // of this level.
            if (startNode.next != null) {
                startNode.right!!.next = startNode.next!!.left
            }
            startNode = startNode.next
        }
    }

    private fun cloneTree(original: BinaryTree<Integer>?): BinaryTreeNode<Integer?>? {
        if (original == null) {
            return null
        }
        val cloned: BinaryTreeNode<Integer?> = BinaryTreeNode<Integer?>(original.data)
        cloned.left = cloneTree(original.left)
        cloned.right = cloneTree(original.right)
        return cloned
    }

    @EpiTest(testDataFile = "tree_right_sibling.tsv")
    @Throws(Exception::class)
    fun constructRightSiblingWrapper(executor: TimedExecutor, tree: BinaryTree<Integer>?): List<List<Integer>> {
        val cloned: BinaryTreeNode<Integer?>? = cloneTree(tree)
        executor.run { constructRightSibling(cloned) }
        val result: List<List<Integer>> = ArrayList()
        var levelStart: BinaryTreeNode<Integer?>? = cloned
        while (levelStart != null) {
            val level: List<Integer> = ArrayList()
            var levelIt: BinaryTreeNode<Integer?>? = levelStart
            while (levelIt != null) {
                level.add(levelIt.data)
                levelIt = levelIt.next
            }
            result.add(level)
            levelStart = levelStart.left
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeRightSibling.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class BinaryTreeNode<T>(@get:Override override var data: T) : TreeLike<T, BinaryTreeNode<T>?>() {

        @get:Override
        override var left: BinaryTreeNode<T>? = null

        @get:Override
        override var right: BinaryTreeNode<T>? = null
        var next: BinaryTreeNode<T>? = null // Populates this field.
    }
}