package epi

import epi.test_framework.EpiTest

object SearchInList {
    fun searchList(L: ListNode<Integer>?, key: Int): ListNode<Integer>? {
        var L: ListNode<Integer>? = L
        while (L != null && L.data !== key) {
            L = L.next
        }
        // If key was not present in the list, L will have become null.
        return L
    }

    @EpiTest(testDataFile = "search_in_list.tsv")
    fun searchListWrapper(L: ListNode<Integer>?, key: Int): Int {
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