package epi

import epi.test_framework.EpiTest

object IntAsListAdd {
    @EpiTest(testDataFile = "int_as_list_add.tsv")
    fun addTwoNumbers(L1: ListNode<Integer>?,
                      L2: ListNode<Integer>?): ListNode<Integer?>? {
        var L1: ListNode<Integer>? = L1
        var L2: ListNode<Integer>? = L2
        val dummyHead: ListNode<Integer?> = ListNode(0, null)
        var placeIter: ListNode<Integer?> = dummyHead
        var carry = 0
        while (L1 != null || L2 != null || carry != 0) {
            val `val`: Int = carry + (if (L1 != null) L1.data else 0) + if (L2 != null) L2.data else 0
            L1 = if (L1 != null) L1.next else null
            L2 = if (L2 != null) L2.next else null
            placeIter!!.next = ListNode(`val` % 10, null)
            carry = `val` / 10
            placeIter = placeIter!!.next
        }
        return dummyHead.next
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsListAdd.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}