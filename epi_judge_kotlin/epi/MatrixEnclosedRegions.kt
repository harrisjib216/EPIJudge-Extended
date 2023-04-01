package epi

import epi.test_framework.EpiTest

object MatrixEnclosedRegions {
    fun fillSurroundedRegions(board: List<List<Character?>?>?) {
        // TODO - you fill in here.
        return
    }

    @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
    fun fillSurroundedRegionsWrapper(board: List<List<Character?>?>?): List<List<Character?>?>? {
        fillSurroundedRegions(board)
        return board
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}