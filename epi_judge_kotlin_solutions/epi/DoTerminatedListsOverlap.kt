package epi

import epi.test_framework.EpiTest

object DoTerminatedListsOverlap {
    fun overlappingNoCycleLists(l0: ListNode<Integer>?, l1: ListNode<Integer>?): ListNode<Integer>? {
        var l0: ListNode<Integer>? = l0
        var l1: ListNode<Integer>? = l1
        val l0Length = length(l0)
        val l1Length = length(l1)

        // Advances the longer list to get equal length lists.
        if (l0Length > l1Length) {
            l0 = advanceListByK(l0Length - l1Length, l0)
        } else {
            l1 = advanceListByK(l1Length - l0Length, l1)
        }
        while (l0 != null && l1 != null && l0 !== l1) {
            l0 = l0.next
            l1 = l1.next
        }
        return l0 // nullptr implies there is no overlap between l0 and l1.
    }

    fun advanceListByK(k: Int, l: ListNode<Integer>?): ListNode<Integer>? {
        var k = k
        var l: ListNode<Integer>? = l
        while (k-- > 0) {
            l = l!!.next
        }
        return l
    }

    private fun length(l: ListNode<Integer>?): Int {
        var l: ListNode<Integer>? = l
        var length = 0
        while (l != null) {
            ++length
            l = l.next
        }
        return length
    }

    @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
    @Throws(Exception::class)
    fun overlappingNoCycleListsWrapper(executor: TimedExecutor, l0: ListNode<Integer>?,
                                       l1: ListNode<Integer>?, common: ListNode<Integer>?) {
        var l0: ListNode<Integer>? = l0
        var l1: ListNode<Integer>? = l1
        if (common != null) {
            if (l0 != null) {
                var i: ListNode<Integer>? = l0
                while (i!!.next != null) {
                    i = i!!.next
                }
                i!!.next = common
            } else {
                l0 = common
            }
            if (l1 != null) {
                var i: ListNode<Integer>? = l1
                while (i!!.next != null) {
                    i = i!!.next
                }
                i!!.next = common
            } else {
                l1 = common
            }
        }
        val finalL0: ListNode<Integer>? = l0
        val finalL1: ListNode<Integer>? = l1
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