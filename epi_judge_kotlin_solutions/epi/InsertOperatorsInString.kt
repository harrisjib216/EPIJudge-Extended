package epi

import epi.test_framework.EpiTest

object InsertOperatorsInString {
    @EpiTest(testDataFile = "insert_operators_in_string.tsv")
    fun expressionSynthesis(digits: List<Integer>, target: Int): Boolean {
        return directedExpressionSynthesis(digits, target, 0, 0, ArrayList(),
                ArrayList())
    }

    private fun directedExpressionSynthesis(digits: List<Integer>, target: Int, currentTerm: Int,
                                            offset: Int, operands: List<Integer>,
                                            operators: List<Character>): Boolean {
        var currentTerm = currentTerm
        currentTerm = currentTerm * 10 + digits[offset]
        if (offset == digits.size() - 1) {
            operands.add(currentTerm)
            if (evaluate(operands, operators) == target) { // Found a match.
                return true
            }
            operands.remove(operands.size() - 1)
            return false
        }

        // No operator.
        if (directedExpressionSynthesis(digits, target, currentTerm, offset + 1,
                        operands, operators)) {
            return true
        }
        // Tries multiplication operator '*'.
        operands.add(currentTerm)
        operators.add('*')
        if (directedExpressionSynthesis(digits, target,  /*currentTerm=*/0,
                        offset + 1, operands, operators)) {
            return true
        }
        operators.remove(operators.size() - 1)
        operands.remove(operands.size() - 1)
        // Tries addition operator '+'.
        operands.add(currentTerm)
        val remainingInt: Int = digits.subList(offset + 1, digits.size())
                .stream()
                .mapToInt(Integer::intValue)
                .reduce(0) { `val`, d -> `val` * 10 + d }
        if (target - evaluate(operands, operators) <= remainingInt) {
            operators.add('+')
            if (directedExpressionSynthesis(digits, target,  /*currentTerm=*/0,
                            offset + 1, operands, operators)) {
                return true
            }
            operators.remove(operators.size() - 1)
        }
        operands.remove(operands.size() - 1)
        return false
    }

    private fun evaluate(operands: List<Integer>,
                         operators: List<Character>): Int {
        val intermediateOperands: Deque<Integer> = ArrayDeque()
        var operandIdx = 0
        intermediateOperands.addFirst(operands[operandIdx++])
        // Evaluates '*' first.
        for (oper in operators) {
            if (oper == '*') {
                intermediateOperands.addFirst(intermediateOperands.removeFirst() *
                        operands[operandIdx++])
            } else {
                intermediateOperands.addFirst(operands[operandIdx++])
            }
        }

        // Evaluates '+' second.
        return intermediateOperands.stream().mapToInt(Integer::intValue).sum()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "InsertOperatorsInString.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}