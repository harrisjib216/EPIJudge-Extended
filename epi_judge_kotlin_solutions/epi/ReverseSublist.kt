package epi

import epi.test_framework.EpiTest

object ReverseSublist {
    @EpiTest(testDataFile = "reverse_sublist.tsv")
    fun reverseSublist(L: ListNode<Integer>?, start: Int,
                       finish: Int): ListNode<Integer?>? {
        var start = start
        val dummyHead: ListNode<Integer?> = ListNode(0, L)
        var sublistHead: ListNode<Integer?> = dummyHead
        var k = 1
        while (k++ < start) {
            sublistHead = sublistHead!!.next
        }

        // Reverse sublist.
        val sublistIter: ListNode<Integer?> = sublistHead!!.next
        while (start++ < finish) {
            val temp: ListNode<Integer?> = sublistIter!!.next
            sublistIter!!.next = temp!!.next
            temp!!.next = sublistHead!!.next
            sublistHead!!.next = temp
        }
        return dummyHead.next
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseSublist.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}