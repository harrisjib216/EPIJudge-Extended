package epi

import epi.test_framework.EpiTest

object IsValidParenthesization {
    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    fun isWellFormed(s: String?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidParenthesization.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}