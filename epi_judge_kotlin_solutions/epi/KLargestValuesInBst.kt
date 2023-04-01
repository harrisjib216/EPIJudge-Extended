package epi

import epi.test_framework.EpiTest

object KLargestValuesInBst {
    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
    fun findKLargestInBst(tree: BstNode<Integer>?, k: Int): List<Integer> {
        val kLargestElements: List<Integer> = ArrayList()
        findKLargestInBstHelper(tree, k, kLargestElements)
        return kLargestElements
    }

    private fun findKLargestInBstHelper(tree: BstNode<Integer>?, k: Int,
                                        kLargestElements: List<Integer>) {
        // Perform reverse inorder traversal.
        if (tree != null && kLargestElements.size() < k) {
            findKLargestInBstHelper(tree.right, k, kLargestElements)
            if (kLargestElements.size() < k) {
                kLargestElements.add(tree.data)
                findKLargestInBstHelper(tree.left, k, kLargestElements)
            }
        }
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