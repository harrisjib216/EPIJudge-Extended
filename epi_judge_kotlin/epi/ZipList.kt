package epi

import epi.test_framework.EpiTest

object ZipList {
    @EpiTest(testDataFile = "zip_list.tsv")
    fun zippingLinkedList(L: ListNode<Integer?>?): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ZipList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}