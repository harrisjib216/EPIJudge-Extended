package epi

import epi.test_framework.EpiTest

object IntAsListAdd {
    @EpiTest(testDataFile = "int_as_list_add.tsv")
    fun addTwoNumbers(L1: ListNode<Integer?>?,
                      L2: ListNode<Integer?>?): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsListAdd.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}