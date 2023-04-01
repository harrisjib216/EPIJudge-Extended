package epi

import epi.test_framework.EpiTest

object RemoveDuplicatesFromSortedList {
    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    fun removeDuplicates(L: ListNode<Integer?>?): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}