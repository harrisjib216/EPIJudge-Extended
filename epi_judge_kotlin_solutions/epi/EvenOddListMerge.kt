package epi

import epi.test_framework.EpiTest

object EvenOddListMerge {
    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    fun evenOddMerge(L: ListNode<Integer>?): ListNode<Integer>? {
        if (L == null) {
            return L
        }
        val evenDummyHead: ListNode<Integer> = ListNode(0, null)
        val oddDummyHead: ListNode<Integer> = ListNode(0, null)
        val tails: List<ListNode<Integer>> = Arrays.asList(evenDummyHead, oddDummyHead)
        var turn = 0
        var iter: ListNode<Integer>? = L
        while (iter != null) {
            tails[turn].next = iter
            tails.set(turn, tails[turn].next)
            turn = turn xor 1
            iter = iter.next
        }
        tails[1].next = null
        tails[0].next = oddDummyHead.next
        return evenDummyHead.next
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvenOddListMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}