package epi

import epi.test_framework.EpiTest

object StringDecompositionsIntoDictionaryWords {
    @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
    fun findAllSubstrings(s: String?, words: List<String?>?): List<Integer>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, "StringDecompositionsIntoDictionaryWords.java",
                        object : Object() {}.getClass().getEnclosingClass())
                .ordinal())
    }
}