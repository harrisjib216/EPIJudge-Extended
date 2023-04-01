package epi

import epi.test_framework.EpiTest

object MaxProductAllButOne {
    @EpiTest(testDataFile = "max_product_all_but_one.tsv")
    fun findBiggestProductNMinusOneProduct(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxProductAllButOne.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}