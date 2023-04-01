package epi

import epi.test_framework.EpiTest

object DeleteNodeFromList {
    // Assumes nodeToDelete is not tail.
    fun deletionFromList(nodeToDelete: ListNode<Integer?>?) {
        nodeToDelete!!.data = nodeToDelete!!.next!!.data
        nodeToDelete!!.next = nodeToDelete!!.next!!.next
    }

    @EpiTest(testDataFile = "delete_node_from_list.tsv")
    @Throws(Exception::class)
    fun deleteListWrapper(executor: TimedExecutor,
                          head: ListNode<Integer?>?,
                          nodeToDeleteIdx: Int): ListNode<Integer?>? {
        var nodeToDeleteIdx = nodeToDeleteIdx
        var nodeToDelete: ListNode<Integer?> = head ?: throw RuntimeException("List is empty")
        while (nodeToDeleteIdx-- > 0) {
            if (nodeToDelete!!.next == null) throw RuntimeException("Can't delete last node")
            nodeToDelete = nodeToDelete!!.next
        }
        val finalNodeToDelete: ListNode<Integer?> = nodeToDelete
        executor.run { deletionFromList(finalNodeToDelete) }
        return head
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteNodeFromList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}