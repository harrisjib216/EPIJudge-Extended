package epi

import epi.test_framework.EpiTest

object SortList {
    @EpiTest(testDataFile = "sort_list.tsv")
    fun stableSortList(L: ListNode<Integer>?): ListNode<Integer>? {

        // Base cases: L is empty or a single node, nothing to do.
        if (L == null || L.next == null) {
            return L
        }

        // Find the midpoint of L using a slow and a fast pointer.
        var preSlow: ListNode<Integer>? = null
        var slow: ListNode<Integer>? = L
        var fast: ListNode<Integer>? = L
        while (fast != null && fast.next != null) {
            preSlow = slow
            fast = fast.next.next
            slow = slow!!.next
        }
        if (preSlow != null) {
            preSlow.next = null // Splits the list into two equal-sized lists.
        }
        return SortedListsMerge.mergeTwoSortedLists(stableSortList(L),
                stableSortList(slow))
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}