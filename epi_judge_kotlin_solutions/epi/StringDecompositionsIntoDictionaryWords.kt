package epi

import epi.test_framework.EpiTest

object StringDecompositionsIntoDictionaryWords {
    @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
    fun findAllSubstrings(s: String, words: List<String>): List<Integer> {
        val wordToFreq: Map<String, Long> = words.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()))
        val unitSize: Int = words[0].length()
        val result: List<Integer> = ArrayList()
        var i = 0
        while (i + unitSize * words.size() <= s.length()) {
            if (matchAllWordsInDict(s, wordToFreq, i, words.size(), unitSize)) {
                result.add(i)
            }
            ++i
        }
        return result
    }

    private fun matchAllWordsInDict(s: String,
                                    wordToFreq: Map<String, Long>,
                                    start: Int, numWords: Int,
                                    unitSize: Int): Boolean {
        val currStringToFreq: Map<String, Integer> = HashMap()
        for (i in 0 until numWords) {
            val currWord: String = s.substring(start + i * unitSize, start + (i + 1) * unitSize)
            val freq = wordToFreq[currWord] ?: return false
            currStringToFreq.put(currWord,
                    currStringToFreq.getOrDefault(currWord, 0) + 1)
            if (currStringToFreq[currWord] > freq) {
                // currWord occurs too many times for a match to be possible.
                return false
            }
        }
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, "StringDecompositionsIntoDictionaryWords.java",
                        object : Object() {}.getClass().getEnclosingClass())
                .ordinal())
    }
}