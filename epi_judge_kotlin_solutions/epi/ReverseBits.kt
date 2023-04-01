package epi

import epi.test_framework.EpiTest

object ReverseBits {
    private val precomputedReverse = LongArray(1 shl 16)
    private fun reverseBits(x: Long, n: Int): Long {
        var x = x
        var i = 0
        var j = n
        while (i < j) {
            x = SwapBits.swapBits(x, i, j)
            ++i
            --j
        }
        return x
    }

    init {
        for (i in 0 until (1 shl 16)) {
            precomputedReverse[i] = reverseBits(i.toLong(), 15)
        }
    }

    @EpiTest(testDataFile = "reverse_bits.tsv")
    fun reverseBits(x: Long): Long {
        val MASK_SIZE = 16
        val BIT_MASK = 0xFFFF
        return precomputedReverse[(x and BIT_MASK.toLong()).toInt()] shl 3 * MASK_SIZE or (precomputedReverse[(x ushr MASK_SIZE and BIT_MASK.toLong()).toInt()] shl 2) * MASK_SIZE or (precomputedReverse[(x ushr 2 * MASK_SIZE and BIT_MASK.toLong()).toInt()]
                shl MASK_SIZE) or
                precomputedReverse[(x ushr 3 * MASK_SIZE and BIT_MASK.toLong()).toInt()]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseBits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}