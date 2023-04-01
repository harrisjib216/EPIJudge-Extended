package epi

import epi.test_framework.EpiTest

object ReverseSublist {
    @EpiTest(testDataFile = "reverse_sublist.tsv")
    fun reverseSublist(L: ListNode<Integer?>?, start: Int,
                       finish: Int): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseSublist.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}