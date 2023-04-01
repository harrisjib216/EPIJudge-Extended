package epi

import epi.test_framework.EpiTest

object NumberOfScoreCombinations {
    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    fun numCombinationsForFinalScore(finalScore: Int,
                                     individualPlayScores: List<Integer>): Int {
        val numCombinationsForScore = Array(individualPlayScores.size()) { IntArray(finalScore + 1) }
        for (i in 0 until individualPlayScores.size()) {
            numCombinationsForScore[i][0] = 1 // One way to reach 0.
            for (j in 1..finalScore) {
                val withoutThisPlay = if (i - 1 >= 0) numCombinationsForScore[i - 1][j] else 0
                val withThisPlay = if (j >= individualPlayScores[i]) numCombinationsForScore[i][j - individualPlayScores[i]] else 0
                numCombinationsForScore[i][j] = withoutThisPlay + withThisPlay
            }
        }
        return numCombinationsForScore[individualPlayScores.size() - 1][finalScore]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}