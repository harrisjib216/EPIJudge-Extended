package epi

import epi.test_framework.EpiTest

object TreeWithParentInorder {
    @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
    fun inorderTraversal(tree: BinaryTree<Integer?>?): List<Integer> {
        var prev: BinaryTree<Integer?>? = null
        var curr: BinaryTree<Integer?>? = tree
        val result: List<Integer> = ArrayList()
        while (curr != null) {
            var next: BinaryTree<Integer?>
            next = if (curr.parent === prev) {
                // We came down to curr from prev.
                if (curr.left != null) { // Keep going left.
                    curr.left
                } else {
                    result.add(curr.data)
                    // Done with left, so go right if right is not empty.
                    // Otherwise, go up.
                    if (curr.right != null) curr.right else curr.parent
                }
            } else if (curr.left === prev) {
                result.add(curr.data)
                // Done with left, so go right if right is not empty. Otherwise, go up.
                if (curr.right != null) curr.right else curr.parent
            } else { // Done with both children, so move up.
                curr.parent
            }
            prev = curr
            curr = next
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeWithParentInorder.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}