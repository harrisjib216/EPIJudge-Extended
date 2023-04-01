package epi

import epi.test_framework.EpiTest

object Knapsack {
    @EpiTest(testDataFile = "knapsack.tsv")
    fun optimumSubjectToCapacity(items: List<Item?>?, capacity: Int): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Knapsack.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Integer::class, Integer::class])
    class Item(weight: Integer, value: Integer) {
        var weight: Integer
        var value: Integer

        init {
            this.weight = weight
            this.value = value
        }
    }
}