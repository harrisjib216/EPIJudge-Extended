package epi

import epi.test_framework.EpiTest

object ElementAppearingOnce {
    @EpiTest(testDataFile = "element_appearing_once.tsv")
    fun findElementAppearsOnce(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ElementAppearingOnce.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}