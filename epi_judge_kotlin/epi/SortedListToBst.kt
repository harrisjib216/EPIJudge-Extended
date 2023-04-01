package epi

import epi.test_framework.EpiTest

object SortedListToBst {
    // Returns the root of the corresponding BST. The prev and next fields of the
    // list nodes are used as the BST nodes left and right fields, respectively.
    // The length of the list is given.
    fun buildBSTFromSortedList(l: DoublyListNode<Integer>?, length: Int): DoublyListNode<Integer?>? {
        // TODO - you fill in here.
        return null
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