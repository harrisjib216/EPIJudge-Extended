package epi

import epi.test_framework.EpiTest

object SearchFirstKey {
    @EpiTest(testDataFile = "search_first_key.tsv")
    fun searchFirstOfK(A: List<Integer?>?, k: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstKey.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}