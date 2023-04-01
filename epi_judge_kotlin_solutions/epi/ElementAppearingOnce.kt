package epi

import epi.test_framework.EpiTest

object ElementAppearingOnce {
    @EpiTest(testDataFile = "element_appearing_once.tsv")
    fun findElementAppearsOnce(A: List<Integer?>): Int {
        val counts = IntArray(32)
        for (x in A) {
            for (i in 0..31) {
                counts[i] += x and 1
                x = x shr 1
            }
        }
        return IntStream.range(0, 32).map { i -> counts[i] % 3 shl i }.sum()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ElementAppearingOnce.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}