package epi

import epi.test_framework.EpiTest

object EvaluateRpn {
    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    fun eval(expression: String?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvaluateRpn.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}