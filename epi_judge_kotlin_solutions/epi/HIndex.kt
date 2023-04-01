package epi

import epi.test_framework.EpiTest

object HIndex {
    @EpiTest(testDataFile = "h_index.tsv")
    fun hIndex(citations: List<Integer>): Int {
        Collections.sort(citations)
        val n: Int = citations.size()
        for (i in 0 until n) {
            if (citations[i] >= n - i) {
                return n - i
            }
        }
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "HIndex.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}