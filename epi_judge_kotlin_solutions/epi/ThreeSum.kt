package epi

import epi.test_framework.EpiTest

object ThreeSum {
    @EpiTest(testDataFile = "three_sum.tsv")
    fun hasThreeSum(A: List<Integer>, t: Int): Boolean {
        Collections.sort(A)
        // Finds if the sum of two numbers in A equals to t - a.
        return A.stream().anyMatch { a -> TwoSum.hasTwoSum(A, t - a) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ThreeSum.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}