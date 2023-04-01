package epi

import epi.test_framework.EpiTest

object ReverseList {
    @EpiTest(testDataFile = "reverse_list.tsv")
    fun reverseList(head: ListNode<Integer>?): ListNode<Integer>? {
        var prev: ListNode<Integer>? = null
        var curr: ListNode<Integer>? = head
        while (curr != null) {
            val temp: ListNode<Integer> = curr.next!!
            curr.next = prev
            prev = curr
            curr = temp
        }
        return prev
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}