package epi

import epi.test_framework.EpiTest

object PowerXY {
    @EpiTest(testDataFile = "power_x_y.tsv")
    fun power(x: Double, y: Int): Double {
        var x = x
        var result = 1.0
        var power = y.toLong()
        if (y < 0) {
            power = -power
            x = 1.0 / x
        }
        while (power != 0L) {
            if (power and 1L != 0L) {
                result *= x
            }
            x *= x
            power = power ushr 1
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PowerXY.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}