package epi

import epi.test_framework.EpiTest

object HuffmanCoding {
    @EpiTest(testDataFile = "huffman_coding.tsv")
    fun huffmanEncoding(symbols: List<CharWithFrequency?>?): Double {
        // TODO - you fill in here.
        return 0.0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "HuffmanCoding.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [String::class, Double::class])
    class CharWithFrequency(s: String, freq: Double) {
        var c: Char
        var freq: Double
        var code: String? = null

        init {
            if (s.length() !== 1) {
                throw RuntimeException(
                        "CharWithFrequency parser: string length is not 1")
            }
            c = s.charAt(0)
            this.freq = freq
        }
    }
}