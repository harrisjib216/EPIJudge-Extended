package epi

import epi.test_framework.EpiTest

object SortedListsMerge {
    @EpiTest(testDataFile = "sorted_lists_merge.tsv") //@include
    fun mergeTwoSortedLists(L1: ListNode<Integer>?,
                            L2: ListNode<Integer>?): ListNode<Integer>? {

        // Creates a placeholder for the result.
        var L1: ListNode<Integer>? = L1
        var L2: ListNode<Integer>? = L2
        val dummyHead: ListNode<Integer> = ListNode(0, null)
        var current: ListNode<Integer> = dummyHead
        while (L1 != null && L2 != null) {
            if (L1.data <= L2.data) {
                current!!.next = L1
                L1 = L1.next
            } else {
                current!!.next = L2
                L2 = L2.next
            }
            current = current!!.next
        }

        // Appends the remaining nodes of L1 or L2.
        current!!.next = if (L1 != null) L1 else L2
        return dummyHead.next
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedListsMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}