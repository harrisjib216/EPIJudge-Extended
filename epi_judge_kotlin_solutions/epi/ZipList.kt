package epi

import epi.test_framework.EpiTest

object ZipList {
    @EpiTest(testDataFile = "zip_list.tsv")
    fun zippingLinkedList(L: ListNode<Integer>?): ListNode<Integer>? {
        if (L == null || L.next == null) {
            return L
        }

        // Find the second half of L.
        var slow: ListNode<Integer>? = L
        var fast: ListNode<Integer>? = L
        while (fast != null && fast.next != null) {
            fast = fast.next.next
            slow = slow!!.next
        }
        val firstHalfHead: ListNode<Integer> = L
        var secondHalfHead: ListNode<Integer> = slow!!.next
        slow!!.next = null // Splits the list into two lists.
        secondHalfHead = ReverseList.reverseList(secondHalfHead)

        // Interleave the first half and the reversed of the second half.
        var firstHalfIter: ListNode<Integer> = firstHalfHead
        var secondHalfIter: ListNode<Integer>? = secondHalfHead
        while (secondHalfIter != null) {
            val temp: ListNode<Integer> = secondHalfIter.next!!
            secondHalfIter.next = firstHalfIter!!.next
            firstHalfIter!!.next = secondHalfIter
            firstHalfIter = firstHalfIter!!.next.next
            secondHalfIter = temp
        }
        return L
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ZipList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}