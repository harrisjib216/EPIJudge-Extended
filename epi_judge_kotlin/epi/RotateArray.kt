package epi

import epi.test_framework.EpiTest

object RotateArray {
    fun rotateArray(rotateAmount: Int, A: List<Integer?>?) {
        // TODO - you fill in here.
        return
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