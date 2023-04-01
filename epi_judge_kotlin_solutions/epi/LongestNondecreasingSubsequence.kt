package epi

import epi.test_framework.EpiTest

object LongestNondecreasingSubsequence {
    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
    fun longestNondecreasingSubsequenceLength(A: List<Integer>): Int {

        // maxLength[i] holds the length of the longest nondecreasing subsequence of
        // A.subList(0, i + 1).
        val maxLength: Array<Integer?> = arrayOfNulls<Integer>(A.size())
        Arrays.fill(maxLength, 1)
        for (i in 1 until A.size()) {
            for (j in 0 until i) {
                if (A[i] >= A[j]) {
                    maxLength[i] = Math.max(maxLength[i], maxLength[j] + 1)
                }
            }
        }
        return Collections.max(List.of(maxLength))
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}