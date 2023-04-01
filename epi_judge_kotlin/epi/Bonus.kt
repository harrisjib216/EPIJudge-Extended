package epi

import epi.test_framework.EpiTest

object Bonus {
    @EpiTest(testDataFile = "bonus.tsv")
    fun calculateBonus(productivity: List<Integer?>?): Integer {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Bonus.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class EmployeeData(productivity: Integer, index: Integer) {
        var productivity: Integer
        var index: Integer

        init {
            this.productivity = productivity
            this.index = index
        }
    }
}