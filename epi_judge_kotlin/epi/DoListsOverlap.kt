package epi

import epi.test_framework.EpiTest

object DoListsOverlap {
    fun overlappingLists(l0: ListNode<Integer?>?,
                         l1: ListNode<Integer?>?): ListNode<Integer?>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "do_lists_overlap.tsv")
    @Throws(Exception::class)
    fun overlappingListsWrapper(executor: TimedExecutor, l0: ListNode<Integer?>?,
                                l1: ListNode<Integer?>?, common: ListNode<Integer?>?,
                                cycle0: Int, cycle1: Int) {
        var l0: ListNode<Integer?>? = l0
        var l1: ListNode<Integer?>? = l1
        var cycle0 = cycle0
        var cycle1 = cycle1
        if (common != null) {
            if (l0 == null) {
                l0 = common
            } else {
                var it: ListNode<Integer?> = l0
                while (it.next != null) {
                    it = it.next
                }
                it.next = common
            }
            if (l1 == null) {
                l1 = common
            } else {
                var it: ListNode<Integer?> = l1
                while (it.next != null) {
                    it = it.next
                }
                it.next = common
            }
        }
        if (cycle0 != -1 && l0 != null) {
            var last: ListNode<Integer?> = l0
            while (last.next != null) {
                last = last.next
            }
            var it: ListNode<Integer?> = l0
            while (cycle0-- > 0) {
                if (it == null) {
                    throw RuntimeException("Invalid input data")
                }
                it = it.next
            }
            last.next = it
        }
        if (cycle1 != -1 && l1 != null) {
            var last: ListNode<Integer?> = l1
            while (last.next != null) {
                last = last.next
            }
            var it: ListNode<Integer?> = l1
            while (cycle1-- > 0) {
                if (it == null) {
                    throw RuntimeException("Invalid input data")
                }
                it = it.next
            }
            last.next = it
        }
        val commonNodes: Set<Integer?> = HashSet()
        var it: ListNode<Integer?>? = common
        while (it != null && !commonNodes.contains(it.data)) {
            commonNodes.add(it.data)
            it = it.next
        }
        val finalL0: ListNode<Integer?>? = l0
        val finalL1: ListNode<Integer?>? = l1
        val result: ListNode<Integer> = executor.run { overlappingLists(finalL0, finalL1) }
        if (!(commonNodes.isEmpty() && result == null || result != null && commonNodes.contains(result.data))) {
            throw TestFailure("Invalid result")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DoListsOverlap.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}