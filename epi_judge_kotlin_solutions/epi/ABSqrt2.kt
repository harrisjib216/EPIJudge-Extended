package epi

import epi.test_framework.EpiTest

// These numbers have very interesting property, and people called it ugly
// numbers. It is also called Quadratic integer rings.
object ABSqrt2 {
    @EpiTest(testDataFile = "a_b_sqrt2.tsv")
    fun generateFirstKABSqrt2(k: Int): List<Double> {
        val candidates: SortedSet<Number> = TreeSet { a, b -> Double.compare(a.`val`, b.`val`) }
        // Initial for 0 + 0 * sqrt(2).
        candidates.add(Number(0, 0))
        val result: List<Double> = ArrayList()
        while (result.size() < k) {
            val nextSmallest: Number = candidates.first()
            result.add(nextSmallest.`val`)

            // Add the next two numbers derived from nextSmallest.
            candidates.add(Number(nextSmallest.a + 1, nextSmallest.b))
            candidates.add(Number(nextSmallest.a, nextSmallest.b + 1))
            candidates.remove(nextSmallest)
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ABSqrt2.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class Number(var a: Int, var b: Int) {
        var `val`: Double

        init {
            `val` = a + b * Math.sqrt(2)
        }
    }
}