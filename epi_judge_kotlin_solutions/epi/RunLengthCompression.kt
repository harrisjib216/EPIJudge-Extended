package epi

import epi.test_framework.EpiTest

object RunLengthCompression {
    fun decoding(s: String): String {
        var count = 0
        val result = StringBuilder()
        for (i in 0 until s.length()) {
            val c: Char = s.charAt(i)
            if (Character.isDigit(c)) {
                count = count * 10 + c.code - '0'.code
            } else {              // c is a letter of alphabet.
                while (count > 0) { // Appends count copies of c to result.
                    result.append(c)
                    count--
                }
            }
        }
        return result.toString()
    }

    fun encoding(s: String): String {
        var count = 1
        val ss = StringBuilder()
        for (i in 1..s.length()) {
            if (i == s.length() || s.charAt(i) !== s.charAt(i - 1)) {
                // Found new character so write the count of previous character.
                ss.append(count).append(s.charAt(i - 1))
                count = 1
            } else { // s.charAt(i) == s.charAt(i - 1).
                ++count
            }
        }
        return ss.toString()
    }

    @EpiTest(testDataFile = "run_length_compression.tsv")
    @Throws(TestFailure::class)
    fun rleTester(encoded: String, decoded: String) {
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