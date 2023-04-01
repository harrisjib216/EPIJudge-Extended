package epi

import epi.test_framework.EpiTest

object DefectiveJugs {
    @EpiTest(testDataFile = "defective_jugs.tsv")
    fun checkFeasible(jugs: List<Jug?>?, L: Int, H: Int): Boolean {
        // TODO - you fill in here.
        return true
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
}