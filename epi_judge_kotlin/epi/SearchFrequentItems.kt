package epi

import epi.test_framework.EpiTest

object SearchFrequentItems {
    fun searchFrequentItems(k: Int,
                            stream: Iterable<String?>?): List<String>? {
        // TODO - you fill in here.
        return null
    }

    @EpiTest(testDataFile = "search_frequent_items.tsv")
    fun searchFrequentItemsWrapper(k: Int,
                                   stream: List<String?>?): List<String>? {
        return searchFrequentItems(k, stream)
    }

    @EpiTestComparator
    fun comp(expected: List<String?>, result: List<String?>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFrequentItems.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}