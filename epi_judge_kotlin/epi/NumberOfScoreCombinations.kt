package epi

import epi.test_framework.EpiTest

object NumberOfScoreCombinations {
    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    fun numCombinationsForFinalScore(finalScore: Int,
                                     individualPlayScores: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}