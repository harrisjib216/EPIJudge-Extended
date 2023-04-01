package epi

import epi.test_framework.EpiTest

object MaxOfSlidingWindow {
    @EpiTest(testDataFile = "max_of_sliding_window.tsv")
    fun computeTrafficVolumes(A: List<TrafficElement?>?, w: Int): List<TrafficElement> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxOfSlidingWindow.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Double::class])
    class TrafficElement(var time: Int, var volume: Double) : Comparable<TrafficElement?> {
        @Override
        operator fun compareTo(o: TrafficElement): Int {
            val volumeCmp: Int = Double.compare(volume, o.volume)
            return if (volumeCmp != 0) volumeCmp else time - o.time
        }

        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            return if (o == null || getClass() !== o.getClass()) {
                false
            } else compareTo(o as TrafficElement) == 0
        }

        @Override
        override fun toString(): String {
            return "[$time, $volume]"
        }
    }
}