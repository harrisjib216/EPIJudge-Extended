package epi

import epi.test_framework.EpiTest

object NumberOfTraversalsStaircase {
    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    fun numberOfWaysToTop(top: Int, maximumStep: Int): Int {
        return computeNumberOfWaysToH(top, maximumStep, IntArray(top + 1))
    }

    private fun computeNumberOfWaysToH(n: Int, maximumStep: Int,
                                       numberOfWaysToH: IntArray): Int {
        if (n <= 1) {
            return 1
        }
        if (numberOfWaysToH[n] == 0) {
            var i = 1
            while (i <= maximumStep && n - i >= 0) {
                numberOfWaysToH[n] += computeNumberOfWaysToH(n - i, maximumStep, numberOfWaysToH)
                ++i
            }
        }
        return numberOfWaysToH[n]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}