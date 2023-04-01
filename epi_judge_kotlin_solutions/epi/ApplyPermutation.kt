package epi

import epi.test_framework.EpiTest

object ApplyPermutation {
    fun applyPermutation(perm: List<Integer>, A: List<Integer?>) {
        for (i in 0 until A.size()) {
            while (perm[i] !== i) {
                Collections.swap(A, i, perm[i])
                Collections.swap(perm, i, perm[i])
            }
        }
    }

    @EpiTest(testDataFile = "apply_permutation.tsv")
    fun applyPermutationWrapper(perm: List<Integer>,
                                A: List<Integer?>): List<Integer?> {
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