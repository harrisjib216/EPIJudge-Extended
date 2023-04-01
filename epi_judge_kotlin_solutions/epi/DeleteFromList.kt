package epi

import epi.test_framework.EpiTest

object DeleteFromList {
    // Delete the node immediately following aNode. Assumes aNode is not a tail.
    fun deleteList(aNode: ListNode<Integer?>?) {
        aNode!!.next = aNode!!.next!!.next
    }

    @EpiTest(testDataFile = "delete_from_list.tsv")
    @Throws(Exception::class)
    fun deleteListWrapper(executor: TimedExecutor, head: ListNode<Integer?>?, nodeIdx: Int): ListNode<Integer?>? {
        var nodeIdx = nodeIdx
        var nodeToDelete: ListNode<Integer?>? = head
        var prev: ListNode<Integer?>? = null
        if (nodeToDelete == null) throw RuntimeException("List is empty")
        while (nodeIdx-- > 0) {
            if (nodeToDelete!!.next == null) throw RuntimeException("Can't delete last node")
            prev = nodeToDelete
            nodeToDelete = nodeToDelete!!.next
        }
        val finalPrev: ListNode<Integer?>? = prev
        executor.run { deleteList(finalPrev) }
        return head
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteFromList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}