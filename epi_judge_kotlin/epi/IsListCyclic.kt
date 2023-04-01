package epi

import epi.test_framework.EpiTest

object IsListCyclic {
    fun hasCycle(head: ListNode<Integer>?): ListNode<Integer?>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "is_list_cyclic.tsv")
    @Throws(Exception::class)
    fun HasCycleWrapper(executor: TimedExecutor,
                        head: ListNode<Integer>?, cycleIdx: Int) {
        var cycleLength = 0
        if (cycleIdx != -1) {
            if (head == null) {
                throw RuntimeException("Can't cycle empty list")
            }
            var cycleStart: ListNode<Integer>? = null
            var cursor: ListNode<Integer> = head
            while (cursor.next != null) {
                if (cursor.data === cycleIdx) {
                    cycleStart = cursor
                }
                cursor = cursor.next
                if (cycleStart != null) {
                    cycleLength++
                }
            }
            if (cursor.data === cycleIdx) {
                cycleStart = cursor
            }
            if (cycleStart == null) {
                throw RuntimeException("Can't find a cycle start")
            }
            cursor.next = cycleStart
            cycleLength++
        }
        val result: ListNode<Integer?> = executor.run { hasCycle(head) }
        if (cycleIdx == -1) {
            if (result != null) {
                throw TestFailure("Found a non-existing cycle")
            }
        } else {
            if (result == null) {
                throw TestFailure("Existing cycle was not found")
            }
            var cursor: ListNode<Integer?> = result
            do {
                cursor = cursor!!.next
                cycleLength--
                if (cursor == null || cycleLength < 0) {
                    throw TestFailure(
                            "Returned node does not belong to the cycle or is not the closest node to the head")
                }
            } while (cursor !== result)
            if (cycleLength != 0) {
                throw TestFailure(
                        "Returned node does not belong to the cycle or is not the closest node to the head")
            }
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsListCyclic.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}