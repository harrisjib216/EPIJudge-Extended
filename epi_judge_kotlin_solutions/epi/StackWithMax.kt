package epi

import epi.test_framework.EpiTest

object StackWithMax {
    @EpiTest(testDataFile = "stack_with_max.tsv")
    @Throws(TestFailure::class)
    fun stackTester(ops: List<StackOp>) {
        try {
            var s = Stack()
            var result: Int
            for (op in ops) {
                when (op.op) {
                    "Stack" -> s = Stack()
                    "push" -> s.push(op.arg)
                    "pop" -> {
                        result = s.pop()
                        if (result != op.arg) {
                            throw TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(result))
                        }
                    }
                    "max" -> {
                        result = s.max()
                        if (result != op.arg) {
                            throw TestFailure("Max: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(result))
                        }
                    }
                    "empty" -> {
                        result = if (s.empty()) 1 else 0
                        if (result != op.arg) {
                            throw TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(s))
                        }
                    }
                    else -> throw RuntimeException("Unsupported stack operation: " + op.op)
                }
            }
        } catch (e: NoSuchElementException) {
            throw TestFailure("Unexpected NoSuchElement exception")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StackWithMax.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class ElementWithCachedMax(element: Integer?, max: Integer) {
        var element: Integer?
        var max: Integer

        init {
            this.element = element
            this.max = max
        }
    }

    class Stack {
        // Stores (element, cached maximum) pair.
        private val elementWithCachedMax: Deque<ElementWithCachedMax> = ArrayDeque()
        fun empty(): Boolean {
            return elementWithCachedMax.isEmpty()
        }

        fun max(): Integer {
            return elementWithCachedMax.peek().max
        }

        fun pop(): Integer {
            return elementWithCachedMax.removeFirst().element
        }

        fun push(x: Integer?) {
            elementWithCachedMax.addFirst(
                    ElementWithCachedMax(x, Math.max(x, if (empty()) x else max())))
        }
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class StackOp(var op: String, var arg: Int)
}