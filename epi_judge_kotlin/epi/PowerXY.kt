package epi

import epi.test_framework.EpiTest

object PowerXY {
    @EpiTest(testDataFile = "power_x_y.tsv")
    fun power(x: Double, y: Int): Double {
        // TODO - you fill in here.
        return 0.0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PowerXY.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}