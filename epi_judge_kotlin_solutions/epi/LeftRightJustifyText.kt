package epi

import epi.test_framework.EpiTest

object LeftRightJustifyText {
    @EpiTest(testDataFile = "left_right_justify_text.tsv")
    fun justifyText(words: List<String>, L: Int): List<String> {
        var currLineLength = 0
        val result: List<String> = ArrayList()
        var currLine: List<StringBuilder> = ArrayList()
        for (word in words) {
            if (currLineLength + word.length() + currLine.size() > L) {
                // Distribute equally between words in curr_line.
                for (i in 0 until L - currLineLength) {
                    currLine[i % Math.max(currLine.size() - 1, 1)].append(' ')
                }
                result.add(String.join("", currLine))
                currLine = ArrayList()
                currLineLength = 0
            }
            currLine.add(StringBuilder(word))
            currLineLength += word.length()
        }
        // Handles the last line. Last line is to be left-aligned.
        result.add(
                String.join(" ", currLine) +
                        String.join("", Collections.nCopies(
                                L - currLineLength - (currLine.size() - 1), " ")))
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LeftRightJustifyText.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}