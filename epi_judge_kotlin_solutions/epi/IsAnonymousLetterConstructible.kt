package epi

import epi.test_framework.EpiTest

object IsAnonymousLetterConstructible {
    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
    fun isLetterConstructibleFromMagazine(letterText: String,
                                          magazineText: String): Boolean {

        // Compute the frequencies for all chars in letterText.
        val charFrequencyForLetter: Map<Character, Long> = letterText.chars()
                .mapToObj { c -> c }
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()))

        // Check if the characters in magazineText can cover characters in
        // letterText.
        for (c in magazineText.toCharArray()) {
            if (charFrequencyForLetter.containsKey(c)) {
                charFrequencyForLetter.put(c, charFrequencyForLetter[c]!! - 1)
                if (charFrequencyForLetter.remove(c, 0L)) {
                    charFrequencyForLetter.remove(c)
                    // All characters for letterText are matched.
                    if (charFrequencyForLetter.isEmpty()) {
                        break
                    }
                }
            }
        }
        // Empty charFrequencyForLetter means every char in letterText can be
        // covered by a character in magazineText.
        return charFrequencyForLetter.isEmpty()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}