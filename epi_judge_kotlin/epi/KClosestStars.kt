package epi

import epi.test_framework.EpiTest

object KClosestStars {
    fun findClosestKStars(stars: Iterator<Star?>?, k: Int): List<Star> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    @EpiTest(testDataFile = "k_closest_stars.tsv")
    fun findClosestKStarsWrapper(stars: List<Star?>, k: Int): List<Star> {
        return findClosestKStars(stars.iterator(), k)
    }

    @EpiTestExpectedType
    var expectedType: List<Double>? = null
    @EpiTestComparator
    fun comp(expected: List<Double>, result: List<Star>): Boolean {
        if (expected.size() !== result.size()) {
            return false
        }
        Collections.sort(result)
        for (i in 0 until result.size()) {
            if (result[i].distance() !== expected[i]) {
                return false
            }
        }
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KClosestStars.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Double::class, Double::class, Double::class])
    class Star(private val x: Double, private val y: Double, private val z: Double) : Comparable<Star?> {
        fun distance(): Double {
            return Math.sqrt(x * x + y * y + z * z)
        }

        @Override
        operator fun compareTo(that: Star): Int {
            return Double.compare(distance(), that.distance())
        }

        @Override
        override fun toString(): String {
            return String.valueOf(distance())
        }
    }
}