package epi

import epi.test_framework.EpiTest

class QueueWithMax {
    private val enqueue: StackWithMax.Stack = Stack()
    private val dequeue: StackWithMax.Stack = Stack()
    fun enqueue(x: Integer?) {
        enqueue.push(x)
    }

    fun dequeue(): Integer {
        if (dequeue.empty()) {
            while (!enqueue.empty()) {
                dequeue.push(enqueue.pop())
            }
        }
        return dequeue.pop()
    }

    fun max(): Integer {
        return if (!enqueue.empty()) {
            if (dequeue.empty()) enqueue.max() else Math.max(enqueue.max(), dequeue.max())
        } else dequeue.max()
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class QueueOp(var op: String, var arg: Int)
    companion object {
        @EpiTest(testDataFile = "queue_with_max.tsv")
        @Throws(TestFailure::class)
        fun queueTester(ops: List<QueueOp>) {
            try {
                var q = QueueWithMax()
                for (op in ops) {
                    when (op.op) {
                        "QueueWithMax" -> q = QueueWithMax()
                        "enqueue" -> q.enqueue(op.arg)
                        "dequeue" -> {
                            val result: Int = q.dequeue()
                            if (result != op.arg) {
                                throw TestFailure("Dequeue: expected " +
                                        String.valueOf(op.arg) + ", got " +
                                        String.valueOf(result))
                            }
                        }
                        "max" -> {
                            val s: Int = q.max()
                            if (s != op.arg) {
                                throw TestFailure("Max: expected " + String.valueOf(op.arg) +
                                        ", got " + String.valueOf(s))
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
                            .runFromAnnotations(args, "QueueWithMax.java",
                                    object : Object() {}.getClass().getEnclosingClass())
                            .ordinal())
        }
    }
}