package epi

import epi.test_framework.EpiTest

object IsStringDecomposableIntoWords {
    fun decomposeIntoDictionaryWords(domain: String?, dictionary: Set<String?>?): List<String> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
    @Throws(Exception::class)
    fun decomposeIntoDictionaryWordsWrapper(executor: TimedExecutor,
                                            domain: String?,
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