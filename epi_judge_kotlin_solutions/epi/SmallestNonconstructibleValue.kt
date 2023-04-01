package epi

import epi.test_framework.EpiTest

object SmallestNonconstructibleValue {
    @EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")
    fun smallestNonconstructibleValue(A: List<Integer?>): Int {
        Collections.sort(A)
        var maxConstructibleValue = 0
        for (a in A) {
            if (a > maxConstructibleValue + 1) {
                break
            }
            maxConstructibleValue += a
        }
        return maxConstructibleValue + 1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SmallestNonconstructibleValue.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}