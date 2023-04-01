package epi

import epi.test_framework.EpiTest

object SortedListToBst {
    private var head: DoublyListNode<Integer>? = null

    // Returns the root of the corresponding BST. The prev and next fields of the
    // list nodes are used as the BST nodes left and right fields, respectively.
    // The length of the list is given.
    fun buildBSTFromSortedList(l: DoublyListNode<Integer>?, length: Int): DoublyListNode<Integer>? {
        head = l
        return buildSortedListHelper(0, length)
    }

    // Builds a BST from the (start + 1)-th to the end-th node, inclusive, in l,
    // and returns the root.
    private fun buildSortedListHelper(start: Int,
                                      end: Int): DoublyListNode<Integer>? {
        if (start >= end) {
            return null
        }
        val mid = start + (end - start) / 2
        val left: DoublyListNode<Integer>? = buildSortedListHelper(start, mid)
        // Previous function call sets head to the successor of the maximum node in
        // the tree rooted at left.
        val curr: DoublyListNode<Integer>? = head
        head!!.prev = left
        head = head!!.next
        curr!!.next = buildSortedListHelper(mid + 1, end)
        return curr
    }

    @Throws(TestFailure::class)
    fun compareVectorAndTree(tree: DoublyListNode<Integer>?,
                             it: Iterator<Integer>) {
        if (tree == null) {
            return
        }
        compareVectorAndTree(tree.prev, it)
        if (!it.hasNext()) {
            throw TestFailure("Too few values in the tree")
        }
        if (it.next() !== tree.data) {
            throw TestFailure("Unexpected value")
        }
        compareVectorAndTree(tree.next, it)
    }

    @EpiTest(testDataFile = "sorted_list_to_bst.tsv")
    @Throws(Exception::class)
    fun buildBSTFromSortedListWrapper(executor: TimedExecutor,
                                      l: List<Integer>) {
        var inputList: DoublyListNode<Integer>? = null
        for (i in l.size() - 1 downTo 0) {
            inputList = DoublyListNode(l[i], null, inputList)
            if (inputList!!.next != null) {
                inputList!!.next.prev = inputList
            }
        }
        val finalList: DoublyListNode<Integer>? = inputList
        inputList = executor.run { buildBSTFromSortedList(finalList, l.size()) }
        val current: Iterator<Integer> = l.iterator()
        compareVectorAndTree(inputList, current)
        if (current.hasNext()) {
            throw TestFailure("Too many l in the tree")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedListToBst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}