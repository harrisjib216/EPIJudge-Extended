package epi

import epi.test_framework.EpiTest

object ReverseWords {
    fun reverseWords(input: CharArray) {
        val n = input.size
        // First, reverses the whole string.
        reverse(input, 0, n - 1)

        // Second, Reverses each word in the string.
        var start = 0
        var finish = 0
        while (start < n) {
            while (start < finish || start < n && input[start] == ' ') {
                ++start // Skip spaces chars.
            }
            while (finish < start || finish < n && input[finish] != ' ') {
                ++finish // Skip non-spaces chars.
            }
            reverse(input, start, finish - 1)
        }
    }

    private fun reverse(array: CharArray, start: Int, end: Int) {
        var start = start
        var end = end
        while (start < end) {
            val tmp = array[start]
            array[start++] = array[end]
            array[end--] = tmp
        }
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