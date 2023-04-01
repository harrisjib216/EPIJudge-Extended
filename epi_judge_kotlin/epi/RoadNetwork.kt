package epi

import epi.test_framework.EpiTest

object RoadNetwork {
    @EpiTest(testDataFile = "road_network.tsv")
    fun findBestProposals(H: List<HighwaySection?>?, P: List<HighwaySection?>?, n: Int): HighwaySection {
        // TODO - you fill in here.
        return HighwaySection(0, 0, 0)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RoadNetwork.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class, Int::class])
    class HighwaySection(var x: Int, var y: Int, var distance: Int) {
        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val that = o as HighwaySection
            return x == that.x && y == that.y && distance == that.distance
        }

        @Override
        override fun toString(): String {
            return "[$x, $y, $distance]"
        }
    }
}