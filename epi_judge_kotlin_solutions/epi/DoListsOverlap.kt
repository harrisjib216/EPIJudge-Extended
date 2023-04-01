package epi

import epi.test_framework.EpiTest

object DoListsOverlap {
    fun overlappingLists(l0: ListNode<Integer>?,
                         l1: ListNode<Integer>?): ListNode<Integer>? {

        // Store the start of cycle if any.
        val root0: ListNode<Integer> = IsListCyclic.hasCycle(l0)
        val root1: ListNode<Integer> = IsListCyclic.hasCycle(l1)
        if (root0 == null && root1 == null) {
            // Both lists don't have cycles.
            return DoTerminatedListsOverlap.overlappingNoCycleLists(l0, l1)
        } else if (root0 != null && root1 == null || root0 == null && root1 != null) {
            // One list has cycle, and one list has no cycle.
            return null
        }
        // Both lists have cycles.
        var temp: ListNode<Integer> = root1
        do {
            temp = temp!!.next
        } while (temp !== root0 && temp !== root1)
        return if (temp === root0) root1 else null
    }

    // Calculates the distance between a and b.
    private fun distance(a: ListNode<Integer?>, b: ListNode<Integer>): Int {
        var a: ListNode<Integer?>? = a
        var dis = 0
        while (a !== b) {
            a = a!!.next
            ++dis
        }
        return dis
    }

    @EpiTest(testDataFile = "do_lists_overlap.tsv")
    @Throws(Exception::class)
    fun overlappingListsWrapper(executor: TimedExecutor, l0: ListNode<Integer>?,
                                l1: ListNode<Integer>?, common: ListNode<Integer>?,
                                cycle0: Int, cycle1: Int) {
        var l0: ListNode<Integer>? = l0
        var l1: ListNode<Integer>? = l1
        var cycle0 = cycle0
        var cycle1 = cycle1
        if (common != null) {
            if (l0 == null) {
                l0 = common
            } else {
                var it: ListNode<Integer>? = l0
                while (it!!.next != null) {
                    it = it!!.next
                }
                it!!.next = common
            }
            if (l1 == null) {
                l1 = common
            } else {
                var it: ListNode<Integer>? = l1
                while (it!!.next != null) {
                    it = it!!.next
                }
                it!!.next = common
            }
        }
        if (cycle0 != -1 && l0 != null) {
            var last: ListNode<Integer>? = l0
            while (last!!.next != null) {
                last = last!!.next
            }
            var it: ListNode<Integer>? = l0
            while (cycle0-- > 0) {
                if (it == null) {
                    throw RuntimeException("Invalid input data")
                }
                it = it.next
            }
            last!!.next = it
        }
        if (cycle1 != -1 && l1 != null) {
            var last: ListNode<Integer>? = l1
            while (last!!.next != null) {
                last = last!!.next
            }
            var it: ListNode<Integer>? = l1
            while (cycle1-- > 0) {
                if (it == null) {
                    throw RuntimeException("Invalid input data")
                }
                it = it.next
            }
            last!!.next = it
        }
        val commonNodes: Set<Integer> = HashSet()
        var it: ListNode<Integer>? = common
        while (it != null && !commonNodes.contains(it.data)) {
            commonNodes.add(it.data)
            it = it.next
        }
        val finalL0: ListNode<Integer>? = l0
        val finalL1: ListNode<Integer>? = l1
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