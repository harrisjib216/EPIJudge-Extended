package epi

import epi.test_framework.EpiTest

object ConvertBase {
    @EpiTest(testDataFile = "convert_base.tsv")
    fun convertBase(numAsString: String, b1: Int, b2: Int): String {
        val isNegative: Boolean = numAsString.startsWith("-")
        val numAsInt: Int = numAsString.substring(if (isNegative) 1 else 0)
                .chars()
                .reduce(0
                ) { x, c ->
                    x * b1 +
                            if (Character.isDigit(c)) c - '0' else c - 'A' + 10
                }
        return (if (isNegative) "-" else "") +
                if (numAsInt == 0) "0" else constructFromBase(numAsInt, b2)
    }

    private fun constructFromBase(numAsInt: Int, base: Int): String {
        return if (numAsInt == 0) "" else constructFromBase(numAsInt / base, base) + (if (numAsInt % base >= 10) 'A'.code + numAsInt % base - 10 else '0'.code + numAsInt % base).toChar()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}