package epi

import epi.test_framework.EpiTest

object SnakeString {
    @EpiTest(testDataFile = "snake_string.tsv")
    fun snakeString(s: String?): String {
        // TODO - you fill in here.
        return ""
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SnakeString.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}