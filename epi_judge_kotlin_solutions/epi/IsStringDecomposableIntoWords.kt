package epi

import epi.test_framework.EpiTest

object IsStringDecomposableIntoWords {
    fun decomposeIntoDictionaryWords(domain: String, dictionary: Set<String?>): List<String> {
        val lastLength = IntArray(domain.length())
        Arrays.fill(lastLength, -1)
        // When the algorithm finishes, lastLength[i] != -1 indicates
        // domain.substring(0, i + 1) has a valid decomposition, and the length of
        // the last string in the decomposition will be lastLength[i].
        for (i in 0 until domain.length()) {
            // If domain.substring(0, i + 1) is a dictionary word, set lastLength[i]
            // to the length of that word.
            if (dictionary.contains(domain.substring(0, i + 1))) {
                lastLength[i] = i + 1
                continue
            }

            // If domain.substring(0, i + 1) is not a dictionary word, we look for j <
            // i such that domain.substring(0, j + 1) has a valid decomposition and
            // domain.substring(j + 1, i + 1) is a dictionary word. If so, record the
            // length of that word in lastLength[i].
            for (j in 0 until i) {
                if (lastLength[j] != -1 &&
                        dictionary.contains(domain.substring(j + 1, i + 1))) {
                    lastLength[i] = i - j
                    break
                }
            }
        }
        val decompositions: List<String> = ArrayList()
        if (lastLength[lastLength.size - 1] != -1) {
            // domain can be assembled by valid words.
            var idx: Int = domain.length() - 1
            while (idx >= 0) {
                decompositions.add(
                        domain.substring(idx + 1 - lastLength[idx], idx + 1))
                idx -= lastLength[idx]
            }
            Collections.reverse(decompositions)
        }
        return decompositions
    }

    @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
    @Throws(Exception::class)
    fun decomposeIntoDictionaryWordsWrapper(executor: TimedExecutor,
                                            domain: String,
                                            dictionary: Set<String?>,
                                            decomposable: Boolean?) {
        val result: List<String> = executor.run { decomposeIntoDictionaryWords(domain, dictionary) }
        if (!decomposable!!) {
            if (!result.isEmpty()) {
                throw TestFailure("domain is not decomposable")
            }
            return
        }
        if (result.stream().anyMatch { s -> !dictionary.contains(s) }) {
            throw TestFailure("Result uses words not in dictionary")
        }
        if (!String.join("", result).equals(domain)) {
            throw TestFailure("Result is not composed into domain")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringDecomposableIntoWords.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}