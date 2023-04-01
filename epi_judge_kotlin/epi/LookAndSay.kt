package epi

import epi.test_framework.EpiTest

object LookAndSay {
    @EpiTest(testDataFile = "look_and_say.tsv")
    fun lookAndSay(n: Int): String {
        // TODO - you fill in here.
        return ""
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LookAndSay.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}