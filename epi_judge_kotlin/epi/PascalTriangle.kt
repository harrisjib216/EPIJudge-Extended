package epi

import epi.test_framework.EpiTest

object PascalTriangle {
    @EpiTest(testDataFile = "pascal_triangle.tsv")
    fun generatePascalTriangle(numRows: Int): List<List<Integer>>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PascalTriangle.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}