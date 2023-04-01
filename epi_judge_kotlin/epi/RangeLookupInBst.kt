package epi

import epi.test_framework.EpiTest

object RangeLookupInBst {
    @EpiTest(testDataFile = "range_lookup_in_bst.tsv")
    fun rangeLookupInBst(tree: BstNode<Integer?>?,
                         interval: Interval?): List<Integer> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    fun rangeLookupInBstHelper(tree: BstNode<Integer>?,
                               interval: Interval,
                               result: List<Integer?>) {
        if (tree == null) {
            return
        }
        if (interval.left <= tree.data && tree.data <= interval.right) {
            // tree.data lies in the interval.
            rangeLookupInBstHelper(tree.left, interval, result)
            result.add(tree.data)
            rangeLookupInBstHelper(tree.right, interval, result)
        } else if (interval.left > tree.data) {
            rangeLookupInBstHelper(tree.right, interval, result)
        } else { // interval.right >= tree.data
            rangeLookupInBstHelper(tree.left, interval, result)
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RangeLookupInBst.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Interval(var left: Int, var right: Int)
}