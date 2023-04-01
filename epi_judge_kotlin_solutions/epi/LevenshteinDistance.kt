package epi

import epi.test_framework.EpiTest

object LevenshteinDistance {
    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    fun levenshteinDistance(A: String, B: String): Int {
        val distanceBetweenPrefixes = Array(A.length()) { IntArray(B.length()) }
        for (row in distanceBetweenPrefixes) {
            Arrays.fill(row, -1)
        }
        return computeDistanceBetweenPrefixes(A, A.length() - 1, B, B.length() - 1,
                distanceBetweenPrefixes)
    }

    private fun computeDistanceBetweenPrefixes(A: String, aIdx: Int, B: String, bIdx: Int,
                                               distanceBetweenPrefixes: Array<IntArray>): Int {
        if (aIdx < 0) {
            // A is empty so add all of B's characters.
            return bIdx + 1
        } else if (bIdx < 0) {
            // B is empty so delete all of A's characters.
            return aIdx + 1
        }
        if (distanceBetweenPrefixes[aIdx][bIdx] == -1) {
            if (A.charAt(aIdx) === B.charAt(bIdx)) {
                distanceBetweenPrefixes[aIdx][bIdx] = computeDistanceBetweenPrefixes(
                        A, aIdx - 1, B, bIdx - 1, distanceBetweenPrefixes)
            } else {
                val substituteLast = computeDistanceBetweenPrefixes(
                        A, aIdx - 1, B, bIdx - 1, distanceBetweenPrefixes)
                val addLast = computeDistanceBetweenPrefixes(A, aIdx, B, bIdx - 1,
                        distanceBetweenPrefixes)
                val deleteLast = computeDistanceBetweenPrefixes(
                        A, aIdx - 1, B, bIdx, distanceBetweenPrefixes)
                distanceBetweenPrefixes[aIdx][bIdx] = 1 + Math.min(substituteLast, Math.min(addLast, deleteLast))
            }
        }
        return distanceBetweenPrefixes[aIdx][bIdx]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LevenshteinDistance.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}