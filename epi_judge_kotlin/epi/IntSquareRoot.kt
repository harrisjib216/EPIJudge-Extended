package epi

import epi.test_framework.EpiTest

object IntSquareRoot {
    @EpiTest(testDataFile = "int_square_root.tsv")
    fun squareRoot(k: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntSquareRoot.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}