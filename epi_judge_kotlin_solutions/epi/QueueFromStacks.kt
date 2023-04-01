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
        private val enqueue: Deque<Integer> = ArrayDeque()
        private val dequeue: Deque<Integer> = ArrayDeque()
        fun enqueue(x: Integer?) {
            enqueue.addFirst(x)
        }

        fun dequeue(): Integer {
            if (dequeue.isEmpty()) {
                // Transfers the elements from enqueue to dequeue.
                while (!enqueue.isEmpty()) {
                    dequeue.addFirst(enqueue.removeFirst())
                }
            }
            return dequeue.removeFirst()
        }
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class QueueOp(var op: String, var arg: Int)
}