package epi

import epi.test_framework.EpiTest

object QueueFromStacks {
    @EpiTest(testDataFile = "queue_from_stacks.tsv")
    @Throws(TestFailure::class)
    fun queueTester(ops: List<QueueOp>) {
        try {
            var q = Queue()
            for (op in ops) {
                when (op.op) {
                    "QueueWithMax" -> q = Queue()
                    "enqueue" -> q.enqueue(op.arg)
                    "dequeue" -> {
                        val result: Int = q.dequeue()
                        if (result != op.arg) {
                            throw TestFailure("Dequeue: expected " +
                                    String.valueOf(op.arg) + ", got " +
                                    String.valueOf(result))
                        }
                    }
                }
            }
        } catch (e: NoSuchElementException) {
            throw TestFailure("Unexpected NoSuchElement exception")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "QueueFromStacks.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class Queue {
        fun enqueue(x: Integer?) {
            // TODO - you fill in here.
            return
        }

        fun dequeue(): Integer {
            // TODO - you fill in here.
            return 0
        }
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class QueueOp(var op: String, var arg: Int)
}