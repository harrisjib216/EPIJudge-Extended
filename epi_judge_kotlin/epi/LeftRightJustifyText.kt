package epi

import epi.test_framework.EpiTest

object LeftRightJustifyText {
    @EpiTest(testDataFile = "left_right_justify_text.tsv")
    fun justifyText(words: List<String?>?, L: Int): List<String>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LeftRightJustifyText.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}