package epi

import epi.test_framework.EpiTest

object SmallestSubarrayCoveringAllValues {
    fun findSmallestSequentiallyCoveringSubset(paragraph: List<String?>,
                                               keywords: List<String?>): Subarray {

        // Maps each keyword to its index in the keywords array.
        val keywordToIdx: Map<String?, Integer> = HashMap()

        // Since keywords are uniquely identified by their indices in keywords
        // array, we can use those indices as keys to lookup in a vector.
        val latestOccurrence: List<Integer> = ArrayList(keywords.size())

        // For each keyword (identified by its index in keywords array), stores the
        // length of the shortest subarray ending at the most recent occurrence of
        // that keyword that sequentially cover all keywords up to that keyword.
        val shortestSubarrayLength: List<Integer> = ArrayList(keywords.size())

        // Initializes latestOccurrence, shortestSubarrayLength, and keywordToIdx.
        for (i in 0 until keywords.size()) {
            latestOccurrence.add(-1)
            shortestSubarrayLength.add(Integer.MAX_VALUE)
            keywordToIdx.put(keywords[i], i)
        }
        var shortestDistance: Int = Integer.MAX_VALUE
        val result = Subarray(-1, -1)
        for (i in 0 until paragraph.size()) {
            val keywordIdx: Integer? = keywordToIdx[paragraph[i]]
            if (keywordIdx != null) {
                if (keywordIdx == 0) { // First keyword.
                    shortestSubarrayLength.set(0, 1)
                } else if (shortestSubarrayLength[keywordIdx - 1] !==
                        Integer.MAX_VALUE) {
                    val distanceToPreviousKeyword: Int = i - latestOccurrence[keywordIdx - 1]
                    shortestSubarrayLength.set(
                            keywordIdx, distanceToPreviousKeyword +
                            shortestSubarrayLength[keywordIdx - 1])
                }
                latestOccurrence.set(keywordIdx, i)

                // Last keyword, look for improved subarray.
                if (keywordIdx === keywords.size() - 1 &&
                        shortestSubarrayLength[shortestSubarrayLength.size() - 1] <
                        shortestDistance) {
                    shortestDistance = shortestSubarrayLength[shortestSubarrayLength.size() - 1]
                    result.start = i -
                            shortestSubarrayLength[shortestSubarrayLength.size() - 1] + 1
                    result.end = i
                }
            }
        }
        return result
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