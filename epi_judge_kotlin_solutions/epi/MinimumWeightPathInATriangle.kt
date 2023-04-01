package epi

import epi.test_framework.EpiTest

object MinimumWeightPathInATriangle {
    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    fun minimumPathTotal(triangle: List<List<Integer?>?>): Int {
        if (triangle.isEmpty()) {
            return 0
        }

        // As we iterate, prevRow stores the minimum path sum to each entry in
        // triangle.get(i - 1).
        var prevRow: List<Integer> = ArrayList(triangle[0])
        for (i in 1 until triangle.size()) {
            // Stores the minimum path sum to each entry in triangle.get(i).
            val currRow: List<Integer> = ArrayList(triangle[i])
            // For the first element.
            currRow.set(0, currRow[0] + prevRow[0])
            for (j in 1 until currRow.size() - 1) {
                currRow.set(j, currRow[j] +
                        Math.min(prevRow[j - 1], prevRow[j]))
            }
            // For the last element
            currRow.set(currRow.size() - 1, currRow[currRow.size() - 1] +
                    prevRow[prevRow.size() - 1])
            prevRow = currRow
        }
        return Collections.min(prevRow)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumWeightPathInATriangle.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}