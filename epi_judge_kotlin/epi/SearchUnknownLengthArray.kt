package epi

import epi.test_framework.EpiTest

object SearchUnknownLengthArray {
    @EpiTest(testDataFile = "search_unknown_length_array.tsv")
    fun binarySearchUnknownLength(A: List<Integer?>?, k: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchUnknownLengthArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}