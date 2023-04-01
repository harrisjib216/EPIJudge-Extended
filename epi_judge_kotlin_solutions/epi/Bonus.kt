package epi

import epi.test_framework.EpiTest

object Bonus {
    private const val DEFAULT_INITIAL_CAPACITY = 16
    @EpiTest(testDataFile = "bonus.tsv")
    fun calculateBonus(productivity: List<Integer>): Integer {
        val minHeap: PriorityQueue<EmployeeData> = PriorityQueue(
                DEFAULT_INITIAL_CAPACITY
        ) { o1, o2 -> Integer.compare(o1.productivity, o2.productivity) }
        for (i in 0 until productivity.size()) {
            minHeap.add(EmployeeData(productivity[i], i))
        }

        // Initially assigns one ticket to everyone.
        val tickets: List<Integer> = ArrayList(Collections.nCopies(productivity.size(), 1))
        // Fills tickets from lowest rating to highest rating.
        while (!minHeap.isEmpty()) {
            val p: EmployeeData = minHeap.peek()
            val nextDev: Int = minHeap.peek().index
            // Handles the left neighbor.
            if (nextDev > 0 &&
                    productivity[nextDev] > productivity[nextDev - 1]) {
                tickets.set(nextDev, tickets[nextDev - 1] + 1)
            }
            // Handles the right neighbor.
            if (nextDev + 1 < tickets.size() &&
                    productivity[nextDev] > productivity[nextDev + 1]) {
                tickets.set(nextDev, Math.max(tickets[nextDev],
                        tickets[nextDev + 1] + 1))
            }
            minHeap.remove(p)
        }
        return tickets.stream().mapToInt(Integer::intValue).sum()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Bonus.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class EmployeeData(productivity: Integer, index: Integer) {
        var productivity: Integer
        var index: Integer

        init {
            this.productivity = productivity
            this.index = index
        }
    }
}