package epi

import epi.test_framework.EpiTest

object ApplyPermutation {
    fun applyPermutation(perm: List<Integer?>?, A: List<Integer?>?) {
        // TODO - you fill in here.
        return
    }

    @EpiTest(testDataFile = "apply_permutation.tsv")
    fun applyPermutationWrapper(perm: List<Integer?>?,
                                A: List<Integer?>?): List<Integer?>? {
        applyPermutation(perm, A)
        return A
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ApplyPermutation.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}