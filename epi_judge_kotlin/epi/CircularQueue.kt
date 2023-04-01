package epi

import epi.test_framework.EpiTest

object CircularQueue {
    @EpiTest(testDataFile = "circular_queue.tsv")
    @Throws(TestFailure::class)
    fun queueTester(ops: List<QueueOp>) {
        var q = Queue(1)
        var opIdx = 0
        for (op in ops) {
            when (op.op) {
                "Queue" -> q = Queue(op.arg)
                "enqueue" -> q.enqueue(op.arg)
                "dequeue" -> {
                    val result: Int = q.dequeue()
                    if (result != op.arg) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, result)
                    }
                }
                "size" -> {
                    val s = q.size()
                    if (s != op.arg) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, s)
                    }
                }
            }
            opIdx++
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CircularQueue.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class Queue(capacity: Int) {
        fun enqueue(x: Integer?) {
            // TODO - you fill in here.
            return
        }

        fun dequeue(): Integer {
            // TODO - you fill in here.
            return 0
        }

        fun size(): Int {
            // TODO - you fill in here.
            return 0
        }

        @Override
        override fun toString(): String {
            // TODO - you fill in here.
            return super.toString()
        }
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class QueueOp(var op: String, var arg: Int) {
        @Override
        override fun toString(): String {
            return op
        }
    }
}