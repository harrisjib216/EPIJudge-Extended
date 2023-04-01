package epi

import epi.test_framework.EpiTest

object RemoveDuplicatesFromSortedList {
    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    fun removeDuplicates(L: ListNode<Integer>?): ListNode<Integer>? {
        var iter: ListNode<Integer>? = L
        while (iter != null) {
            // Uses nextDistinct to find the next distinct value.
            var nextDistinct: ListNode<Integer> = iter.next!!
            while (nextDistinct != null && nextDistinct.data === iter.data) {
                nextDistinct = nextDistinct.next!!
            }
            iter.next = nextDistinct
            iter = nextDistinct
        }
        return L
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}