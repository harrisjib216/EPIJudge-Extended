package epi

import epi.test_framework.EpiTest

object PrimitiveDivide {
    @EpiTest(testDataFile = "primitive_divide.tsv")
    fun divide(x: Int, y: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveDivide.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}