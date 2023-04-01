package epi

import epi.test_framework.EpiTest

object Hanoi {
    private const val NUM_PEGS = 3
    fun computeTowerHanoi(numRings: Int): List<List<Integer>> {
        val pegs: List<Deque<Integer>> = IntStream.range(0, NUM_PEGS)
                .mapToObj { i -> ArrayDeque<Integer>() }
                .collect(Collectors.toList())
        // Initialize pegs.
        for (i in numRings downTo 1) {
            pegs[0].addFirst(i)
        }
        val result: List<List<Integer>> = ArrayList()
        computeTowerHanoiSteps(numRings, pegs, 0, 1, 2, result)
        return result
    }

    private fun computeTowerHanoiSteps(numRingsToMove: Int,
                                       pegs: List<Deque<Integer>>,
                                       fromPeg: Int, toPeg: Int, usePeg: Int,
                                       result: List<List<Integer>>) {
        if (numRingsToMove > 0) {
            computeTowerHanoiSteps(numRingsToMove - 1, pegs, fromPeg, usePeg, toPeg,
                    result)
            pegs[toPeg].addFirst(pegs[fromPeg].removeFirst())
            result.add(List.of(fromPeg, toPeg))
            computeTowerHanoiSteps(numRingsToMove - 1, pegs, usePeg, toPeg, fromPeg,
                    result)
        }
    }

    @EpiTest(testDataFile = "hanoi.tsv")
    @Throws(Exception::class)
    fun computeTowerHanoiWrapper(executor: TimedExecutor,
                                 numRings: Int) {
        val pegs: List<Deque<Integer>> = ArrayList()
        for (i in 0 until NUM_PEGS) {
            pegs.add(LinkedList())
        }
        for (i in numRings downTo 1) {
            pegs[0].addFirst(i)
        }
        val result: List<List<Integer>> = executor.run { computeTowerHanoi(numRings) }
        for (operation in result) {
            val fromPeg: Int = operation[0]
            val toPeg: Int = operation[1]
            if (!pegs[toPeg].isEmpty() &&
                    pegs[fromPeg].getFirst() >= pegs[toPeg].getFirst()) {
                throw TestFailure("Illegal move from " +
                        String.valueOf(pegs[fromPeg].getFirst()) +
                        " to " +
                        String.valueOf(pegs[toPeg].getFirst()))
            }
            pegs[toPeg].addFirst(pegs[fromPeg].removeFirst())
        }
        val expectedPegs1: List<Deque<Integer>> = ArrayList()
        for (i in 0 until NUM_PEGS) {
            expectedPegs1.add(LinkedList<Integer>())
        }
        for (i in numRings downTo 1) {
            expectedPegs1[1].addFirst(i)
        }
        val expectedPegs2: List<Deque<Integer>> = ArrayList()
        for (i in 0 until NUM_PEGS) {
            expectedPegs2.add(LinkedList<Integer>())
        }
        for (i in numRings downTo 1) {
            expectedPegs2[2].addFirst(i)
        }
        if (!pegs.equals(expectedPegs1) && !pegs.equals(expectedPegs2)) {
            throw TestFailure("Pegs doesn't place in the right configuration")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Hanoi.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}