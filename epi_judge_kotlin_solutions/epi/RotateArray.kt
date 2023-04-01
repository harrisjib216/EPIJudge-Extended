package epi

import epi.test_framework.EpiTest

object RotateArray {
    fun rotateArray(rotateAmount: Int, A: List<Integer>) {
        var rotateAmount = rotateAmount
        rotateAmount %= A.size()
        reverse(0, A.size(), A)
        reverse(0, rotateAmount, A)
        reverse(rotateAmount, A.size(), A)
    }

    private fun reverse(begin: Int, end: Int, A: List<Integer>) {
        var i = begin
        var j = end - 1
        while (i < j) {
            Collections.swap(A, i, j)
            ++i
            --j
        }
    }

    @EpiTest(testDataFile = "rotate_array.tsv")
    @Throws(Exception::class)
    fun rotateArrayWrapper(executor: TimedExecutor, A: List<Integer?>?, rotateAmount: Int): List<Integer> {
        val aCopy: List<Integer> = ArrayList(A)
        executor.run { rotateArray(rotateAmount, aCopy) }
        return aCopy
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RotateArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}