package epi

import epi.test_framework.EpiTest

object SmallestSubarrayCoveringSet {
    fun findSmallestSubarrayCoveringSet(paragraph: List<String?>,
                                        keywords: Set<String?>): Subarray {
        val keywordsToCover: Map<String?, Long> = keywords.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()))
        val result = Subarray(-1, -1)
        var remainingToCover: Int = keywords.size()
        var left = 0
        var right = 0
        while (right < paragraph.size()) {
            if (keywordsToCover.containsKey(paragraph[right]) &&
                    keywordsToCover.put(paragraph[right],
                            keywordsToCover[paragraph[right]]!! - 1) >=
                    1) {
                --remainingToCover
            }

            // Keeps advancing left until it reaches end or keywordsToCover does not
            // have all keywords.
            while (remainingToCover == 0) {
                if (result.start == -1 && result.end == -1 ||
                        right - left < result.end - result.start) {
                    result.start = left
                    result.end = right
                }
                if (keywordsToCover.containsKey(paragraph[left]) &&
                        keywordsToCover.put(paragraph[left],
                                keywordsToCover[paragraph[left]]!! + 1) >=
                        0) {
                    ++remainingToCover
                }
                ++left
            }
            ++right
        }
        return result
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    @Throws(Exception::class)
    fun findSmallestSubarrayCoveringSetWrapper(
            executor: TimedExecutor, paragraph: List<String?>, keywords: Set<String?>): Int {
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