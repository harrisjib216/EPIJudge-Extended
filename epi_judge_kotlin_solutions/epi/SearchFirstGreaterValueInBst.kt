package epi

import epi.test_framework.EpiTest

object SearchFirstGreaterValueInBst {
    fun findFirstGreaterThanK(tree: BstNode<Integer>?,
                              k: Integer): BstNode<Integer>? {
        var subtree: BstNode<Integer>? = tree
        var firstSoFar: BstNode<Integer>? = null
        while (subtree != null) {
            if (subtree.data > k) {
                firstSoFar = subtree
                subtree = subtree.left
            } else { // Root and all keys in left-subtree are <= k, so skip them.
                subtree = subtree.right
            }
        }
        return firstSoFar
    }

    @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
    fun findFirstGreaterThanKWrapper(tree: BstNode<Integer>?,
                                     k: Integer): Int {
        val result: BstNode<Integer>? = findFirstGreaterThanK(tree, k)
        return if (result != null) result.data else -1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}