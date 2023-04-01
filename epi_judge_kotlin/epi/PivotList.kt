package epi

import epi.test_framework.EpiTest

object PivotList {
    fun listPivoting(l: ListNode<Integer?>?, x: Int): ListNode<Integer?>? {
        // TODO - you fill in here.
        return null
    }

    fun linkedToList(l: ListNode<Integer?>?): List<Integer> {
        var l: ListNode<Integer?>? = l
        val v: List<Integer> = ArrayList()
        while (l != null) {
            v.add(l.data)
            l = l.next
        }
        return v
    }

    @EpiTest(testDataFile = "pivot_list.tsv")
    @Throws(Exception::class)
    fun listPivotingWrapper(executor: TimedExecutor,
                            l: ListNode<Integer?>?, x: Int) {
        var l: ListNode<Integer?>? = l
        val original: List<Integer> = linkedToList(l)
        val finalL: ListNode<Integer?>? = l
        l = executor.run { listPivoting(finalL, x) }
        val pivoted: List<Integer> = linkedToList(l)
        var mode = -1
        for (i in pivoted) {
            when (mode) {
                -1 -> if (i == x) {
                    mode = 0
                } else if (i > x) {
                    mode = 1
                }
                0 -> if (i < x) {
                    throw TestFailure("List is not pivoted")
                } else if (i > x) {
                    mode = 1
                }
                1 -> if (i <= x) {
                    throw TestFailure("List is not pivoted")
                }
            }
        }
        Collections.sort(original)
        Collections.sort(pivoted)
        if (!original.equals(pivoted)) throw TestFailure("Result list contains different values")
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PivotList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}