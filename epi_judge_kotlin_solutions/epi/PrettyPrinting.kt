package epi

import epi.test_framework.EpiTest

object PrettyPrinting {
    @EpiTest(testDataFile = "pretty_printing.tsv")
    fun minimumMessiness(words: List<String>, lineLength: Int): Int {

        // minimumMessiness[i] is the minimum messiness when placing
        // words.subList(0, i + 1).
        val minimumMessiness = IntArray(words.size())
        Arrays.fill(minimumMessiness, Integer.MAX_VALUE)
        var numRemainingBlanks: Int = lineLength - words[0].length()
        minimumMessiness[0] = numRemainingBlanks * numRemainingBlanks
        for (i in 1 until words.size()) {
            numRemainingBlanks = lineLength - words[i].length()
            minimumMessiness[i] = minimumMessiness[i - 1] + numRemainingBlanks * numRemainingBlanks
            // Try adding words.get(i - 1), words.get(i - 2), ...
            for (j in i - 1 downTo 0) {
                numRemainingBlanks -= words[j].length() + 1
                if (numRemainingBlanks < 0) {
                    // Not enough space to add more words.
                    break
                }
                val firstJMessiness = if (j - 1 < 0) 0 else minimumMessiness[j - 1]
                val currentLineMessiness = numRemainingBlanks * numRemainingBlanks
                minimumMessiness[i] = Math.min(minimumMessiness[i],
                        firstJMessiness + currentLineMessiness)
            }
        }
        return minimumMessiness[words.size() - 1]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrettyPrinting.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}