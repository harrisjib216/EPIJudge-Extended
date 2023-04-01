package epi

import epi.test_framework.EpiTest

object ConvertBase {
    @EpiTest(testDataFile = "convert_base.tsv")
    fun convertBase(numAsString: String?, b1: Int, b2: Int): String {
        // TODO - you fill in here.
        return ""
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}