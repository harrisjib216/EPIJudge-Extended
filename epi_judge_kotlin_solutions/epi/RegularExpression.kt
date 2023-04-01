package epi

import epi.test_framework.EpiTest

object RegularExpression {
    @EpiTest(testDataFile = "regular_expression.tsv")
    fun isMatch(regex: String, s: String): Boolean {

        // Case (2.): regex starts with '^'.
        if (regex.charAt(0) === '^') {
            return isMatchHere(regex.substring(1), s)
        }
        for (i in 0..s.length()) {
            if (isMatchHere(regex, s.substring(i))) {
                return true
            }
        }
        return false
    }

    private fun isMatchHere(regex: String, s: String): Boolean {
        if (regex.isEmpty()) {
            // Case (1.): Empty regex matches all strings.
            return true
        }
        if ("$".equals(regex)) {
            // Case (2): Reach the end of regex, and last char is '$'.
            return s.isEmpty()
        }
        if (regex.length() >= 2 && regex.charAt(1) === '*') {
            // Case (3.): A '*' match.
            // Iterate through s, checking '*' condition, if '*' condition holds,
            // performs the remaining checks.
            var i = 0
            while (i < s.length() && (regex.charAt(0) === '.' ||
                            regex.charAt(0) === s.charAt(i))) {
                if (isMatchHere(regex.substring(2), s.substring(i + 1))) {
                    return true
                }
                ++i
            }
            // See '*' matches zero character in s.
            return isMatchHere(regex.substring(2), s)
        }

        // Case (4.): regex begins with single character match.
        return !s.isEmpty() &&
                (regex.charAt(0) === '.' || regex.charAt(0) === s.charAt(0)) &&
                isMatchHere(regex.substring(1), s.substring(1))
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RegularExpression.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}