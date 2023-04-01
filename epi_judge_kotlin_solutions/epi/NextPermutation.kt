package epi

import epi.test_framework.EpiTest

object NextPermutation {
    @EpiTest(testDataFile = "next_permutation.tsv")
    fun nextPermutation(perm: List<Integer>): List<Integer> {

        // Find the first entry from the right that is smaller than the entry
        // immediately after it.
        var inversionPoint: Int = perm.size() - 2
        while (inversionPoint >= 0 &&
                perm[inversionPoint] >= perm[inversionPoint + 1]) {
            --inversionPoint
        }
        if (inversionPoint == -1) {
            return Collections.emptyList() // perm is the last permutation.
        }

        // Swap the smallest entry after index inversionPoint that is greater than
        // perm.get(inversionPoint). Since entries in perm are decreasing after
        // inversionPoint, if we search in reverse order, the first entry that is
        // greater than perm.get(inversionPoint) is the entry to swap with.
        for (i in perm.size() - 1 downTo inversionPoint + 1) {
            if (perm[i] > perm[inversionPoint]) {
                Collections.swap(perm, inversionPoint, i)
                break
            }
        }

        // Entries in perm must appear in decreasing order after inversionPoint, so
        // we simply reverse these entries to get the smallest dictionary order.
        Collections.reverse(perm.subList(inversionPoint + 1, perm.size()))
        return perm
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NextPermutation.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}