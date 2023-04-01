package epi

import epi.test_framework.EpiTest

object StringTransformability {
    @EpiTest(testDataFile = "string_transformability.tsv")
    fun transformString(D: Set<String?>?, s: String?, t: String?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringTransformability.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}