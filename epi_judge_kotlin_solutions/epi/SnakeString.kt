package epi

import epi.test_framework.EpiTest

object SnakeString {
    @EpiTest(testDataFile = "snake_string.tsv")
    fun snakeString(s: String): String {
        val result = StringBuilder()
        // Outputs the first row, i.e., s[1], s[5], s[9], ...
        run {
            var i = 1
            while (i < s.length()) {
                result.append(s.charAt(i))
                i += 4
            }
        }
        // Outputs the second row, i.e., s[0], s[2], s[4], ...
        run {
            var i = 0
            while (i < s.length()) {
                result.append(s.charAt(i))
                i += 2
            }
        }
        // Outputs the third row, i.e., s[3], s[7], s[11], ...
        var i = 3
        while (i < s.length()) {
            result.append(s.charAt(i))
            i += 4
        }
        return result.toString()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SnakeString.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}