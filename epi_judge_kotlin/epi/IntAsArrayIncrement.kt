package epi

import epi.test_framework.EpiTest

object IntAsArrayIncrement {
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    fun plusOne(A: List<Integer?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}