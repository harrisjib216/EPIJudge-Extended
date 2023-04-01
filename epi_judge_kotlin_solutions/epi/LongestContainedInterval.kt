package epi

import epi.test_framework.EpiTest

object LongestContainedInterval {
    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    fun longestContainedRange(A: List<Integer?>?): Int {

        // unprocessedEntries records the existence of each entry in A.
        val unprocessedEntries: Set<Integer> = HashSet(A)
        var maxIntervalSize = 0
        while (!unprocessedEntries.isEmpty()) {
            val a: Int = unprocessedEntries.iterator().next()
            unprocessedEntries.remove(a)

            // Finds the lower bound of the largest range containing a.
            var lowerBound = a - 1
            while (unprocessedEntries.contains(lowerBound)) {
                unprocessedEntries.remove(lowerBound)
                --lowerBound
            }

            // Finds the upper bound of the largest range containing a.
            var upperBound = a + 1
            while (unprocessedEntries.contains(upperBound)) {
                unprocessedEntries.remove(upperBound)
                ++upperBound
            }
            maxIntervalSize = Math.max(upperBound - lowerBound - 1, maxIntervalSize)
        }
        return maxIntervalSize
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestContainedInterval.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}