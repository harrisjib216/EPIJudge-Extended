package epi

import epi.test_framework.EpiTest

object InsertInList {
    // Insert newNode after node.
    fun insertAfter(node: ListNode<Integer?>?,
                    newNode: ListNode<Integer?>) {
        newNode.next = node!!.next
        node!!.next = newNode
    }

    @EpiTest(testDataFile = "insert_in_list.tsv")
    @Throws(Exception::class)
    fun insertListWrapper(executor: TimedExecutor, l: ListNode<Integer?>?, nodeIdx: Int,
                          newNodeData: Int): ListNode<Integer?>? {
        var nodeIdx = nodeIdx
        var node: ListNode<Integer?>? = l
        while (nodeIdx > 1) {
            node = node!!.next
            --nodeIdx
        }
        val newNode: ListNode<Integer?> = ListNode<Integer?>(newNodeData, null)
        val finalNode: ListNode<Integer?>? = node
        executor.run { insertAfter(finalNode, newNode) }
        return l
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "InsertInList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}