package epi

import epi.test_framework.EpiTest

object DoTerminatedListsOverlap {
    fun overlappingNoCycleLists(l0: ListNode<Integer?>?, l1: ListNode<Integer?>?): ListNode<Integer?>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
    @Throws(Exception::class)
    fun overlappingNoCycleListsWrapper(executor: TimedExecutor, l0: ListNode<Integer?>?,
                                       l1: ListNode<Integer?>?, common: ListNode<Integer?>?) {
        var l0: ListNode<Integer?>? = l0
        var l1: ListNode<Integer?>? = l1
        if (common != null) {
            if (l0 != null) {
                var i: ListNode<Integer?> = l0
                while (i.next != null) {
                    i = i.next
                }
                i.next = common
            } else {
                l0 = common
            }
            if (l1 != null) {
                var i: ListNode<Integer?> = l1
                while (i.next != null) {
                    i = i.next
                }
                i.next = common
            } else {
                l1 = common
            }
        }
        val finalL0: ListNode<Integer?>? = l0
        val finalL1: ListNode<Integer?>? = l1
        val result: ListNode<Integer> = executor.run { overlappingNoCycleLists(finalL0, finalL1) }
        if (result !== common) {
            throw TestFailure("Invalid result")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}