package epi

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import java.util.List

object ABSqrt2 {
    @EpiTest(testDataFile = "a_b_sqrt2.tsv")
    fun generateFirstKABSqrt2(k: Int): List<Double>? {
        // TODO - you fill in here.
        return null
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ABSqrt2.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}