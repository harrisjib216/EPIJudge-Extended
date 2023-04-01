package epi

import epi.test_framework.EpiTest

object BstMerge {
    private var head: BstNode<Integer>? = null
    private fun buildSortedDoublyLinkedList(L: BstNode<Integer>?, n: Int): BstNode<Integer>? {
        head = L
        return buildSortedDoublyLinkedListHelper(0, n)
    }

    // Build a BST from the (s + 1)-th to the e-th node in L.
    private fun buildSortedDoublyLinkedListHelper(s: Int,
                                                  e: Int): BstNode<Integer>? {
        if (s >= e) {
            return null
        }
        val m = s + (e - s) / 2
        val left: BstNode<Integer>? = buildSortedDoublyLinkedListHelper(s, m)
        val curr: BstNode<Integer> = BstNode(head!!.data)
        head = head!!.right
        curr.left = left
        curr.right = buildSortedDoublyLinkedListHelper(m + 1, e)
        return curr
    }

    // Transform a BST into a circular sorted doubly linked list in-place,
    // return the head of the list.
    private fun <T> bstToDoublyLinkedList(n: BstNode<T>?): BstNode<T>? {
        // Empty subtree.
        if (n == null) {
            return null
        }

        // Recursively build the list from left and right subtrees.
        var lHead: BstNode<T>? = bstToDoublyLinkedList<Any>(n.left)
        val rHead: BstNode<T>? = bstToDoublyLinkedList<Any>(n.right)

        // Append n to the list from left subtree.
        var lTail: BstNode<T>? = null
        if (lHead != null) {
            lTail = lHead.left
            lTail!!.right = n
            n.left = lTail
            lTail = n
        } else {
            lTail = n
            lHead = lTail
        }

        // Append the list from right subtree to n.
        var rTail: BstNode<T>? = null
        if (rHead != null) {
            rTail = rHead.left
            lTail.right = rHead
            rHead.left = lTail
        } else {
            rTail = lTail
        }
        rTail!!.right = lHead
        lHead.left = rTail
        return lHead
    }

    // Count the list length till end.
    private fun <T> countLength(L: BstNode<T>?): Int {
        var L: BstNode<T>? = L
        var length = 0
        while (L != null) {
            ++length
            L = L.right
        }
        return length
    }

    @EpiTest(testDataFile = "bst_merge.tsv")
    fun mergeTwoBsts(A: BstNode<Integer>?,
                     B: BstNode<Integer>?): BstNode<Integer>? {
        var A: BstNode<Integer>? = A
        var B: BstNode<Integer>? = B
        A = bstToDoublyLinkedList<Any>(A)
        B = bstToDoublyLinkedList<Any>(B)
        A!!.left!!.right = null
        B!!.left!!.right = null
        A!!.left = null
        B!!.left = null
        val ALength = countLength<Any>(A)
        val BLength = countLength<Any>(B)
        return buildSortedDoublyLinkedList(mergeTwoSortedLinkedLists(A, B),
                ALength + BLength)
    }

    // Merge two sorted linked lists, return the head of list.
    private fun mergeTwoSortedLinkedLists(A: BstNode<Integer>?, B: BstNode<Integer>?): BstNode<Integer>? {
        val dummyHead: BstNode<Integer> = BstNode()
        var current: BstNode<Integer> = dummyHead
        var p1: BstNode<Integer>? = A
        var p2: BstNode<Integer>? = B
        while (p1 != null && p2 != null) {
            if (Integer.compare(p1.data, p2.data) < 0) {
                current!!.right = p1
                p1 = p1.right
            } else {
                current!!.right = p2
                p2 = p2.right
            }
            current = current!!.right
        }
        if (p1 != null) {
            current!!.right = p1
        }
        if (p2 != null) {
            current!!.right = p2
        }
        return dummyHead.right
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BstMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}