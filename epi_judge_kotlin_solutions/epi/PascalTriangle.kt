package epi

import epi.test_framework.EpiTest

object PascalTriangle {
    @EpiTest(testDataFile = "pascal_triangle.tsv")
    fun generatePascalTriangle(numRows: Int): List<List<Integer>> {
        val pascalTriangle: List<List<Integer>> = ArrayList()
        for (i in 0 until numRows) {
            val currRow: List<Integer> = ArrayList()
            for (j in 0..i) {
                // Set this entry to the sum of the two above adjacent entries if they
                // exist.
                currRow.add(if (0 < j && j < i) pascalTriangle[i - 1][j - 1] +
                        pascalTriangle[i - 1][j] else 1)
            }
            pascalTriangle.add(currRow)
        }
        return pascalTriangle
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PascalTriangle.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}