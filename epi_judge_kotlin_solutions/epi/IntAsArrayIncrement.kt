package epi

import epi.test_framework.EpiTest

object IntAsArrayIncrement {
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    fun plusOne(A: List<Integer>): List<Integer> {
        val n: Int = A.size() - 1
        A.set(n, A[n] + 1)
        var i = n
        while (i > 0 && A[i] === 10) {
            A.set(i, 0)
            A.set(i - 1, A[i - 1] + 1)
            --i
        }
        if (A[0] === 10) {
            // There is a carry-out, so we need one more digit to store the result.
            // A slick way to do this is to append a 0 at the end of the array,
            // and update the first entry to 1.
            A.set(0, 1)
            A.add(0)
        }
        return A
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}