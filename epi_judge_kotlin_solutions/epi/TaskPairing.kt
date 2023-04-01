package epi

import epi.test_framework.EpiTest

object TaskPairing {
    @EpiTest(testDataFile = "task_pairing.tsv")
    fun optimumTaskAssignment(taskDurations: List<Integer>): List<PairedTasks> {
        Collections.sort(taskDurations)
        val optimumAssignments: List<PairedTasks> = ArrayList()
        var i = 0
        var j: Int = taskDurations.size() - 1
        while (i < j) {
            optimumAssignments.add(
                    PairedTasks(taskDurations[i], taskDurations[j]))
            ++i
            --j
        }
        return optimumAssignments
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TaskPairing.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Integer::class, Integer::class])
    class PairedTasks(task1: Integer, task2: Integer) {
        var task1: Integer
        var task2: Integer

        init {
            this.task1 = task1
            this.task2 = task2
        }

        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val that = o as PairedTasks
            return task1.equals(that.task1) && task2.equals(that.task2)
        }

        @Override
        override fun toString(): String {
            return "[$task1, $task2]"
        }
    }
}