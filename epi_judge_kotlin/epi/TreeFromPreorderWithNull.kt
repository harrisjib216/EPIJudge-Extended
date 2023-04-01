package epi

import epi.test_framework.EpiTest

object TreeFromPreorderWithNull {
    fun reconstructPreorder(preorder: List<Integer?>?): BinaryTreeNode<Integer?>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
    @Throws(Exception::class)
    fun reconstructPreorderWrapper(executor: TimedExecutor, strings: List<String>): BinaryTreeNode<Integer> {
        val ints: List<Integer> = ArrayList()
        for (s in strings) {
            if (s.equals("null")) {
                ints.add(null)
            } else {
                ints.add(Integer.parseInt(s))
            }
        }
        return executor.run { reconstructPreorder(ints) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}