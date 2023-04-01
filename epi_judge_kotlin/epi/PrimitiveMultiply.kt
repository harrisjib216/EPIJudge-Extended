package epi

import epi.test_framework.EpiTest

object PrimitiveMultiply {
    @EpiTest(testDataFile = "primitive_multiply.tsv")
    fun multiply(x: Long, y: Long): Long {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveMultiply.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}