package epi

import epi.test_framework.EpiTest

object TwoSum {
    @EpiTest(testDataFile = "two_sum.tsv")
    fun hasTwoSum(A: List<Integer>, t: Int): Boolean {
        var i = 0
        var j: Int = A.size() - 1
        while (i <= j) {
            if (A[i] + A[j] === t) {
                return true
            } else if (A[i] + A[j] < t) {
                ++i
            } else { // A[i] + A[j] > t.
                --j
            }
        }
        return false
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TwoSum.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}