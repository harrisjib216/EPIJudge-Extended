package epi

import epi.test_framework.EpiTest

object DeleteKthLastFromList {
    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv") // Assumes L has at least k nodes, deletes the k-th last node in L.
    fun removeKthLast(L: ListNode<Integer>?, k: Int): ListNode<Integer?>? {
        var k = k
        val dummyHead: ListNode<Integer?> = ListNode(0, L)
        var first: ListNode<Integer?> = dummyHead.next
        while (k-- > 0) {
            first = first!!.next
        }
        var second: ListNode<Integer?> = dummyHead
        while (first != null) {
            second = second!!.next
            first = first.next
        }
        // second points to the (k + 1)-th last node, deletes its successor.
        second!!.next = second!!.next!!.next
        return dummyHead.next
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}