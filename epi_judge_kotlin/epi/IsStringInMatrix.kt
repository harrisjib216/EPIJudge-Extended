package epi

import epi.test_framework.EpiTest

object IsStringInMatrix {
    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    fun isPatternContainedInGrid(grid: List<List<Integer?>?>?,
                                 pattern: List<Integer?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringInMatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}