package epi

import epi.test_framework.EpiTest

object SortedListsMerge {
    @EpiTest(testDataFile = "sorted_lists_merge.tsv") //@include
    fun mergeTwoSortedLists(L1: ListNode<Integer?>?,
                            L2: ListNode<Integer?>?): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedListsMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}