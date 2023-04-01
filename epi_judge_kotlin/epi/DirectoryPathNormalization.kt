package epi

import epi.test_framework.EpiTest

object DirectoryPathNormalization {
    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    fun shortestEquivalentPath(path: String?): String {
        // TODO - you fill in here.
        return ""
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}