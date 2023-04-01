package epi

import epi.test_framework.EpiTest

object EvaluateRpn {
    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    fun eval(expression: String): Int {
        val intermediateResults: Deque<Integer> = ArrayDeque()
        val DELIMITER = ","
        val OPERATORS: Map<String, ToIntBiFunction<Integer, Integer>?> = Map.of(
                "+",
                { y, x -> x + y },
                "-", { y, x -> x - y }, "*", { y, x -> x * y }, "/") { y, x -> x / y }
        for (token in expression.split(DELIMITER)) {
            if (OPERATORS[token] != null) {
                intermediateResults.addFirst(
                        OPERATORS[token].applyAsInt(intermediateResults.removeFirst(),
                                intermediateResults.removeFirst()))
            } else { // token is a number.
                intermediateResults.addFirst(Integer.parseInt(token))
            }
        }
        return intermediateResults.removeFirst()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvaluateRpn.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}