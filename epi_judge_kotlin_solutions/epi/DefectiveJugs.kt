package epi

import epi.test_framework.EpiTest

object DefectiveJugs {
    @EpiTest(testDataFile = "defective_jugs.tsv")
    fun checkFeasible(jugs: List<Jug>, L: Int, H: Int): Boolean {
        return checkFeasibleHelper(jugs, L, H, HashSet())
    }

    private fun checkFeasibleHelper(jugs: List<Jug>, L: Int, H: Int,
                                    c: Set<VolumeRange>): Boolean {
        if (L > H || c.contains(VolumeRange(L, H)) || L < 0 && H < 0) {
            return false
        }

        // Checks the volume for each jug to see if it is possible.
        if (jugs.stream().anyMatch { j ->
                    L <= j.low && j.high <= H ||
                            checkFeasibleHelper(jugs, L - j.low, H - j.high, c)
                }) {
            return true
        }
        c.add(VolumeRange(L, H)) // Marks this as impossible.
        return false
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DefectiveJugs.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Jug {
        var low = 0
        var high = 0

        constructor() {}
        constructor(low: Int, high: Int) {
            this.low = low
            this.high = high
        }
    }

    private class VolumeRange(low: Integer, high: Integer) {
        var low: Integer
        var high: Integer

        init {
            this.low = low
            this.high = high
        }

        @Override
        override fun equals(obj: Object?): Boolean {
            if (obj == null || obj !is VolumeRange) {
                return false
            }
            if (this === obj) {
                return true
            }
            val vr = obj as VolumeRange
            return low.equals(vr.low) && high.equals(vr.high)
        }

        @Override
        override fun hashCode(): Int {
            return Objects.hash(low, high)
        }
    }
}