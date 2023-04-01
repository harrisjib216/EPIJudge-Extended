package epi

import epi.test_framework.EpiTest

object KLargestValuesInBst {
    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
    fun findKLargestInBst(tree: BstNode<Integer?>?, k: Int): List<Integer> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    @EpiTestComparator
    fun comp(expected: List<Integer?>, result: List<Integer?>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KLargestValuesInBst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}