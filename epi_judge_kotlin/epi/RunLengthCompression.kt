package epi

import epi.test_framework.EpiTest

object RunLengthCompression {
    fun decoding(s: String?): String {
        // TODO - you fill in here.
        return ""
    }

    fun encoding(s: String?): String {
        // TODO - you fill in here.
        return ""
    }

    @EpiTest(testDataFile = "run_length_compression.tsv")
    @Throws(TestFailure::class)
    fun rleTester(encoded: String?, decoded: String?) {
        if (!decoding(encoded).equals(decoded)) {
            throw TestFailure("Decoding failed")
        }
        if (!encoding(decoded).equals(encoded)) {
            throw TestFailure("Encoding failed")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RunLengthCompression.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}