package epi

import epi.test_framework.EpiTest

object BstToSortedList {
    fun bstToDoublyLinkedList(tree: BstNode<Integer>?): BstNode<Integer>? {
        return bstToDoublyLinkedListHelper(tree).head
    }

    // Transforms a BST into a sorted doubly linked list in-place, and return the
    // head and tail of the list.
    private fun bstToDoublyLinkedListHelper(tree: BstNode<Integer>?): HeadAndTail {
        // Empty subtree.
        if (tree == null) {
            return HeadAndTail(null, null)
        }

        // Recursively build the list from left and right subtrees.
        val left = bstToDoublyLinkedListHelper(tree.left)
        val right = bstToDoublyLinkedListHelper(tree.right)

        // Append tree to the list from left subtree.
        if (left.tail != null) {
            left.tail.right = tree
        }
        tree.left = left.tail

        // Append the list from right subtree to tree.
        tree.right = right.head
        if (right.head != null) {
            right.head.left = tree
        }
        return HeadAndTail(if (left.head != null) left.head else tree,
                if (right.tail != null) right.tail else tree)
    }

    @EpiTest(testDataFile = "bst_to_sorted_list.tsv")
    @Throws(Exception::class)
    fun bstToDoublyLinkedListWrapper(executor: TimedExecutor, tree: BstNode<Integer>?): List<Integer> {
        var list: BstNode<Integer?> = executor.run { bstToDoublyLinkedList(tree) }
        if (list != null && list.left != null) throw TestFailure(
                "Function must return the head of the list. Left link must be null")
        val v: List<Integer> = ArrayList()
        while (list != null) {
            v.add(list.data)
            if (list.right != null && list.right.left !== list) throw RuntimeException("List is ill-formed")
            list = list.right
        }
        return v
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BstToSortedList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class HeadAndTail(head: BstNode<Integer>?, tail: BstNode<Integer>?) {
        var head: BstNode<Integer>?
        var tail: BstNode<Integer>?

        init {
            this.head = head
            this.tail = tail
        }
    }
}