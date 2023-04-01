package epi

import epi.test_framework.EpiTest

object QueueWithMaxUsingDeque {
    @EpiTest(testDataFile = "queue_with_max.tsv")
    @Throws(TestFailure::class)
    fun queueTester(ops: List<QueueOp>) {
        try {
            var q: QueueWithMax<Integer> = QueueWithMax<Integer>()
            for (op in ops) {
                when (op.op) {
                    "QueueWithMax" -> q = QueueWithMax<Integer>()
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
                        .runFromAnnotations(args, "QueueWithMaxUsingDeque.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class QueueWithMax<T : Comparable<T>?> {
        private val entries: Queue<T> = ArrayDeque()
        private val candidatesForMax: Deque<T> = ArrayDeque()
        fun enqueue(x: T) {
            entries.add(x)
            while (!candidatesForMax.isEmpty() &&
                    candidatesForMax.peekLast().compareTo(x) < 0) {
                // Eliminate dominated elements in candidatesForMax.
                candidatesForMax.removeLast()
            }
            candidatesForMax.addLast(x)
        }

        fun dequeue(): T {
            val result: T = entries.remove()
            if (result!!.equals(candidatesForMax.peekFirst())) {
                candidatesForMax.removeFirst()
            }
            return result
        }

        fun max(): T {
            return candidatesForMax.peekFirst()
        }

        fun head(): T {
            return entries.peek()
        }
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class QueueOp(var op: String, var arg: Int)
}