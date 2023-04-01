package epi

import epi.test_framework.EpiTest

object BstToSortedList {
    fun bstToDoublyLinkedList(tree: BstNode<Integer?>?): BstNode<Integer?>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "bst_to_sorted_list.tsv")
    @Throws(Exception::class)
    fun bstToDoublyLinkedListWrapper(executor: TimedExecutor, tree: BstNode<Integer?>?): List<Integer> {
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
}