package epi

import epi.test_framework.EpiTest

object SearchInBst {
    fun searchBST(tree: BstNode<Integer?>?, key: Int): BstNode<Integer>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "search_in_bst.tsv")
    fun searchBSTWrapper(tree: BstNode<Integer?>?, key: Int): Int {
        val result: BstNode<Integer>? = searchBST(tree, key)
        return if (result != null) result.data else -1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchInBst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}