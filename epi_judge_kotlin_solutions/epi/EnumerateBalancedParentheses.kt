package epi

import epi.test_framework.EpiTest

object EnumerateBalancedParentheses {
    @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")
    fun generateBalancedParentheses(numPairs: Int): List<String> {
        val result: List<String> = ArrayList()
        directedGenerateBalancedParentheses(numPairs, numPairs,  /*validPrefix=*/"",
                result)
        return result
    }

    private fun directedGenerateBalancedParentheses(numLeftParensNeeded: Int,
                                                    numRightParensNeeded: Int,
                                                    validPrefix: String, result: List<String>) {
        if (numRightParensNeeded == 0) {
            result.add(validPrefix)
            return
        }
        if (numLeftParensNeeded > 0) { // Able to insert '('.
            directedGenerateBalancedParentheses(numLeftParensNeeded - 1,
                    numRightParensNeeded,
                    "$validPrefix(", result)
        }
        if (numLeftParensNeeded < numRightParensNeeded) { // Able to insert ')'.
            directedGenerateBalancedParentheses(numLeftParensNeeded,
                    numRightParensNeeded - 1,
                    "$validPrefix)", result)
        }
    }

    @EpiTestComparator
    fun comp(expected: List<String?>, result: List<String?>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}