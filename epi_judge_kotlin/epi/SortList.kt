package epi

import epi.test_framework.EpiTest

object SortList {
    @EpiTest(testDataFile = "sort_list.tsv")
    fun stableSortList(L: ListNode<Integer?>?): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}