package epi

import epi.test_framework.EpiTest

object NextPermutation {
    @EpiTest(testDataFile = "next_permutation.tsv")
    fun nextPermutation(perm: List<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NextPermutation.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}