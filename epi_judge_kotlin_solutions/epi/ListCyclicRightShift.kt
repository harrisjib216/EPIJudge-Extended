package epi

import epi.test_framework.EpiTest

object ListCyclicRightShift {
    @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
    fun cyclicallyRightShiftList(L: ListNode<Integer>?,
                                 k: Int): ListNode<Integer>? {
        var k = k
        if (L == null) {
            return L
        }

        // Computes the length of L and the tail.
        var tail: ListNode<Integer>? = L
        var n = 1
        while (tail!!.next != null) {
            ++n
            tail = tail!!.next
        }
        k %= n
        if (k == 0) {
            return L
        }
        tail!!.next = L // Makes a cycle by connecting the tail to the head.
        var stepsToNewHead = n - k
        var newTail: ListNode<Integer>? = tail
        while (stepsToNewHead-- > 0) {
            newTail = newTail!!.next
        }
        val newHead: ListNode<Integer> = newTail!!.next
        newTail!!.next = null
        return newHead
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ListCyclicRightShift.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}