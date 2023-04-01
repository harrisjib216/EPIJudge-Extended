package epi

import epi.test_framework.EpiTest

object SmallestSubarrayCoveringSet {
    fun findSmallestSubarrayCoveringSet(paragraph: List<String?>?,
                                        keywords: Set<String?>?): Subarray {
        // TODO - you fill in here.
        return Subarray(0, 0)
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    @Throws(Exception::class)
    fun findSmallestSubarrayCoveringSetWrapper(
            executor: TimedExecutor, paragraph: List<String?>, keywords: Set<String?>?): Int {
        val copy: Set<String> = HashSet(keywords)
        val result: Subarray = executor.run { findSmallestSubarrayCoveringSet(paragraph, keywords) }
        if (result.start < 0 || result.start >= paragraph.size() || result.end < 0 || result.end >= paragraph.size() || result.start > result.end) throw TestFailure("Index out of range")
        for (i in result.start..result.end) {
            copy.remove(paragraph[i])
        }
        if (!copy.isEmpty()) {
            throw TestFailure("Not all keywords are in the range")
        }
        return result.end - result.start + 1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    // Represent subarray by starting and ending indices, inclusive.
    class Subarray(start: Integer, end: Integer) {
        var start: Integer
        var end: Integer

        init {
            this.start = start
            this.end = end
        }
    }
}