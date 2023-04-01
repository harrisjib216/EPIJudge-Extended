package epi

import epi.test_framework.EpiTest

object IsListPalindromic {
    @EpiTest(testDataFile = "is_list_palindromic.tsv")
    fun isLinkedListAPalindrome(L: ListNode<Integer>?): Boolean {

        // Finds the second half of L.
        var slow: ListNode<Integer>? = L
        var fast: ListNode<Integer>? = L
        while (fast != null && fast.next != null) {
            fast = fast.next.next
            slow = slow!!.next
        }

        // Compare the first half and the reversed second half lists.
        var firstHalfIter: ListNode<Integer>? = L
        var secondHalfIter: ListNode<Integer> = ReverseList.reverseList(slow)
        while (secondHalfIter != null && firstHalfIter != null) {
            if (!secondHalfIter.data.equals(firstHalfIter.data)) {
                return false
            }
            secondHalfIter = secondHalfIter.next
            firstHalfIter = firstHalfIter.next
        }
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