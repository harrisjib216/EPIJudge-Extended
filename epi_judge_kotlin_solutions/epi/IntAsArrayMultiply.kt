package epi

import epi.test_framework.EpiTest

object IntAsArrayMultiply {
    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    fun multiply(num1: List<Integer>, num2: List<Integer>): List<Integer> {
        val sign = if ((num1[0] < 0) xor (num2[0] < 0)) -1 else 1
        num1.set(0, Math.abs(num1[0]))
        num2.set(0, Math.abs(num2[0]))
        var result: List<Integer> = ArrayList(Collections.nCopies(num1.size() + num2.size(), 0))
        for (i in num1.size() - 1 downTo 0) {
            for (j in num2.size() - 1 downTo 0) {
                result.set(i + j + 1,
                        result[i + j + 1] + num1[i] * num2[j])
                result.set(i + j, result[i + j] + result[i + j + 1] / 10)
                result.set(i + j + 1, result[i + j + 1] % 10)
            }
        }

        // Remove the leading zeroes.
        var firstNotZero = 0
        while (firstNotZero < result.size() && result[firstNotZero] === 0) {
            ++firstNotZero
        }
        result = result.subList(firstNotZero, result.size())
        if (result.isEmpty()) {
            return List.of(0)
        }
        result.set(0, result[0] * sign)
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}