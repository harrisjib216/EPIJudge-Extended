package epi

import epi.test_framework.EpiTest

object IsAnonymousLetterConstructible {
    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
    fun isLetterConstructibleFromMagazine(letterText: String?,
                                          magazineText: String?): Boolean {
        // TODO - you fill in here.
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}