package epi

import epi.test_framework.EpiTest

object SmallestSubarrayCoveringAllValues {
    fun findSmallestSequentiallyCoveringSubset(paragraph: List<String?>?,
                                               keywords: List<String?>?): Subarray {
        // TODO - you fill in here.
        return Subarray(0, 0)
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
    @Throws(Exception::class)
    fun findSmallestSequentiallyCoveringSubsetWrapper(
            executor: TimedExecutor, paragraph: List<String>, keywords: List<String?>): Int {
        val result: Subarray = executor.run { findSmallestSequentiallyCoveringSubset(paragraph, keywords) }
        var kwIdx = 0
        if (result.start < 0) {
            throw TestFailure("Subarray start index is negative")
        }
        var paraIdx: Int = result.start
        while (kwIdx < keywords.size()) {
            if (paraIdx >= paragraph.size()) {
                throw TestFailure("Not all keywords are in the generated subarray")
            }
            if (paraIdx >= paragraph.size()) {
                throw TestFailure("Subarray end index exceeds array size")
            }
            if (paragraph[paraIdx].equals(keywords[kwIdx])) {
                kwIdx++
            }
            paraIdx++
        }
        return result.end - result.start + 1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class Subarray(start: Integer, end: Integer) {
        // Represent subarray by starting and ending indices, inclusive.
        var start: Integer
        var end: Integer

        init {
            this.start = start
            this.end = end
        }
    }
}