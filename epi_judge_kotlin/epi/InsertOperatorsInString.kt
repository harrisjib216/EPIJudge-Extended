package epi

import epi.test_framework.EpiTest

object InsertOperatorsInString {
    @EpiTest(testDataFile = "insert_operators_in_string.tsv")
    fun expressionSynthesis(digits: List<Integer?>?, target: Int): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "InsertOperatorsInString.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}