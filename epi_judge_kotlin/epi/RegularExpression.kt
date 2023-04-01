package epi

import epi.test_framework.EpiTest

object RegularExpression {
    @EpiTest(testDataFile = "regular_expression.tsv")
    fun isMatch(regex: String?, s: String?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RegularExpression.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}