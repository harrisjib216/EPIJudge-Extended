package epi

import epi.test_framework.EpiTest

object RomanToInteger {
    @EpiTest(testDataFile = "roman_to_integer.tsv")
    fun romanToInteger(s: String): Int {
        val T: Map<Character, Integer> = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000)
        var sum: Int = T[s.charAt(s.length() - 1)]
        for (i in s.length() - 2 downTo 0) {
            if (T[s.charAt(i)] < T[s.charAt(i + 1)]) {
                sum -= T[s.charAt(i)]
            } else {
                sum += T[s.charAt(i)]
            }
        }
        return sum
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RomanToInteger.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}