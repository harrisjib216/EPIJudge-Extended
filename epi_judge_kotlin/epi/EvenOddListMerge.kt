package epi

import epi.test_framework.EpiTest

object EvenOddListMerge {
    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    fun evenOddMerge(L: ListNode<Integer?>?): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvenOddListMerge.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}