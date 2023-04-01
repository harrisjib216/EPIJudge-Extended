package epi

import epi.test_framework.EpiTest

object ListCyclicRightShift {
    @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
    fun cyclicallyRightShiftList(L: ListNode<Integer?>?,
                                 k: Int): ListNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ListCyclicRightShift.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}