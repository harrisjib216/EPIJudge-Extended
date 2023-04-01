package epi

import epi.test_framework.EpiTest

object SortedArrayRemoveDups {
    // Returns the number of valid entries after deletion.
    fun deleteDuplicates(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
    @Throws(Exception::class)
    fun deleteDuplicatesWrapper(executor: TimedExecutor,
                                A: List<Integer?>): List<Integer?> {
        val end: Int = executor.run { deleteDuplicates(A) }
        return A.subList(0, end)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}