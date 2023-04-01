package epi

import epi.test_framework.EpiTest

object Knapsack {
    @EpiTest(testDataFile = "knapsack.tsv")
    fun optimumSubjectToCapacity(items: List<Item>, capacity: Int): Int {

        // V[i][j] holds the optimum value when we choose from
        // items.subList(0, i + 1) and have a capacity of j.
        val V = Array(items.size()) { IntArray(capacity + 1) }
        for (v in V) {
            Arrays.fill(v, -1)
        }
        return optimumSubjectToItemAndCapacity(items, items.size() - 1, capacity,
                V)
    }

    // Returns the optimum value when we choose from items.subList(0, k + 1) and
    // have a capacity of availableCapacity.
    private fun optimumSubjectToItemAndCapacity(items: List<Item>, k: Int,
                                                availableCapacity: Int,
                                                V: Array<IntArray>): Int {
        if (k < 0) {
            // No items can be chosen.
            return 0
        }
        if (V[k][availableCapacity] == -1) {
            val withoutCurrItem = optimumSubjectToItemAndCapacity(items, k - 1, availableCapacity, V)
            val withCurrItem = if (availableCapacity < items[k].weight) 0 else items[k].value +
                    optimumSubjectToItemAndCapacity(
                            items, k - 1, availableCapacity - items[k].weight,
                            V)
            V[k][availableCapacity] = Math.max(withoutCurrItem, withCurrItem)
        }
        return V[k][availableCapacity]
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