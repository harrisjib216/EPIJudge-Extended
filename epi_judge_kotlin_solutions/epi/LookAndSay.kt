package epi

import epi.test_framework.EpiTest

object LookAndSay {
    @EpiTest(testDataFile = "look_and_say.tsv")
    fun lookAndSay(n: Int): String {
        var s = "1"
        for (i in 1 until n) {
            s = nextNumber(s)
        }
        return s
    }

    private fun nextNumber(s: String): String {
        val result = StringBuilder()
        var i = 0
        while (i < s.length()) {
            var count = 1
            while (i + 1 < s.length() && s.charAt(i) === s.charAt(i + 1)) {
                ++i
                ++count
            }
            result.append(count).append(s.charAt(i))
            ++i
        }
        return result.toString()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LookAndSay.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}