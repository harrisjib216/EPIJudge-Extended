package epi

import epi.test_framework.EpiTest

object DeleteKthLastFromList {
    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv") // Assumes L has at least k nodes, deletes the k-th last node in L.
    fun removeKthLast(L: ListNode<Integer?>?, k: Int): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}