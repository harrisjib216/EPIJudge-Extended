package epi

import epi.test_framework.EpiTest

object ReverseWords {
    fun reverseWords(input: CharArray?) {
        // TODO - you fill in here.
        return
    }

    @EpiTest(testDataFile = "reverse_words.tsv")
    @Throws(Exception::class)
    fun reverseWordsWrapper(executor: TimedExecutor, s: String): String {
        val sCopy: CharArray = s.toCharArray()
        executor.run { reverseWords(sCopy) }
        return String.valueOf(sCopy)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseWords.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}