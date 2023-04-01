package epi

import epi.test_framework.EpiTest

class QueueWithMax {
    fun enqueue(x: Integer?) {
        // TODO - you fill in here.
        return
    }

    fun dequeue(): Integer {
        // TODO - you fill in here.
        return 0
    }

    fun max(): Integer {
        // TODO - you fill in here.
        return 0
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