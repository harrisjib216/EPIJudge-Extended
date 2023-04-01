package epi

import epi.test_framework.EpiTest

object PrimitiveMultiply {
    @EpiTest(testDataFile = "primitive_multiply.tsv")
    fun multiply(x: Long, y: Long): Long {
        var x = x
        var y = y
        var sum: Long = 0
        while (x != 0L) {
            // Examines each bit of x.
            if (x and 1L != 0L) {
                sum = add(sum, y)
            }
            x = x ushr 1
            y = y shl 1
        }
        return sum
    }

    private fun add(a: Long, b: Long): Long {
        return if (b == 0L) a else add(a xor b, a and b shl 1)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveMultiply.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}