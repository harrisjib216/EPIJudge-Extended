package epi

import epi.test_framework.EpiTest

object IsListPalindromic {
    @EpiTest(testDataFile = "is_list_palindromic.tsv")
    fun isLinkedListAPalindrome(L: ListNode<Integer?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsListPalindromic.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}