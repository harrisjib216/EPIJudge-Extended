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
        private var head = 0
        private var tail = 0
        private var numQueueElements = 0
        private var entries: Array<Integer>

        init {
            entries = arrayOfNulls<Integer>(capacity)
        }

        fun enqueue(x: Integer) {
            if (numQueueElements == entries.size) { // Need to resize.
                // Makes the queue elements appear consecutively.
                Collections.rotate(Arrays.asList(entries), -head)
                // Resets head and tail.
                head = 0
                tail = numQueueElements
                entries = Arrays.copyOf(entries, numQueueElements * SCALE_FACTOR)
            }
            entries[tail] = x
            tail = (tail + 1) % entries.size
            ++numQueueElements
        }

        fun dequeue(): Integer {
            --numQueueElements
            val result: Integer = entries[head]
            head = (head + 1) % entries.size
            return result
        }

        fun size(): Int {
            return numQueueElements
        }

        @Override
        override fun toString(): String {
            return ("Queue{"
                    + "head=" + head + ", tail=" + tail +
                    ", entries=" + Arrays.toString(entries) + '}')
        }

        companion object {
            private const val SCALE_FACTOR = 2
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