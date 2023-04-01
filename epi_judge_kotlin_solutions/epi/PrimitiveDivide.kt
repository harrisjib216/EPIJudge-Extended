package epi

import epi.test_framework.EpiTest

object PrimitiveDivide {
    @EpiTest(testDataFile = "primitive_divide.tsv")
    fun divide(x: Int, y: Int): Int {
        var x = x
        var result = 0
        var power = 32
        var yPower = y.toLong() shl power
        while (x >= y) {
            while (yPower > x) {
                yPower = yPower ushr 1
                --power
            }
            result += 1 shl power
            x -= yPower.toInt()
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveDivide.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}