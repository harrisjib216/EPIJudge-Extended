package epi

import epi.test_framework.EpiTest

object SearchInList {
    fun searchList(L: ListNode<Integer?>?, key: Int): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "search_in_list.tsv")
    fun searchListWrapper(L: ListNode<Integer?>?, key: Int): Int {
        val result: ListNode<Integer>? = searchList(L, key)
        return if (result != null) result.data else -1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchInList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}