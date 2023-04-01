package epi

import epi.test_framework.EpiTest

object SmallestNonconstructibleValue {
    @EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")
    fun smallestNonconstructibleValue(A: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestNonconstructibleValue.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}