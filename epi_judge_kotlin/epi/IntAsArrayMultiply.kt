package epi

import epi.test_framework.EpiTest

object IntAsArrayMultiply {
    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    fun multiply(num1: List<Integer?>?, num2: List<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}