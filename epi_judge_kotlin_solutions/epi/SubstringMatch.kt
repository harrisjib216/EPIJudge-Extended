package epi

import epi.test_framework.EpiTest

object SubstringMatch {
    @EpiTest(testDataFile = "substring_match.tsv") // Returns the index of the first character of the substring if found, -1
    // otherwise.
    fun rabinKarp(t: String, s: String): Int {
        if (s.length() > t.length()) {
            return -1 // s is not a substring of t.
        }
        val BASE = 26
        var tHash = 0
        var sHash = 0 // Hash codes for the substring of t and s.
        var powerS = 1 // BASE^|s-1|.
        for (i in 0 until s.length()) {
            powerS = if (i > 0) powerS * BASE else 1
            tHash = tHash * BASE + t.charAt(i)
            sHash = sHash * BASE + s.charAt(i)
        }
        for (i in s.length() until t.length()) {
            // Checks the two substrings are actually equal or not, to protect
            // against hash collision.
            if (tHash == sHash && t.substring(i - s.length(), i).equals(s)) {
                return i - s.length() // Found a match.
            }

            // Uses rolling hash to compute the new hash code.
            tHash -= t.charAt(i - s.length()) * powerS
            tHash = tHash * BASE + t.charAt(i)
        }
        // Tries to match s and t.substring(t.length() - s.length()).
        return if (tHash == sHash && t.substring(t.length() - s.length()).equals(s)) {
            t.length() - s.length()
        } else -1
        // s is not a substring of t.
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SubstringMatch.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}