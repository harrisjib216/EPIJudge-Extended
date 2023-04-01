package epi

import epi.test_framework.EpiTest

object NearestRepeatedEntries {
    @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
    fun findNearestRepetition(paragraph: List<String?>): Int {
        val wordToLatestIndex: Map<String?, Integer> = HashMap()
        var nearestRepeatedDistance: Int = Integer.MAX_VALUE
        for (i in 0 until paragraph.size()) {
            if (wordToLatestIndex.containsKey(paragraph[i])) {
                nearestRepeatedDistance = Math.min(nearestRepeatedDistance,
                        i - wordToLatestIndex[paragraph[i]])
            }
            wordToLatestIndex.put(paragraph[i], i)
        }
        return if (nearestRepeatedDistance != Integer.MAX_VALUE) nearestRepeatedDistance else -1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}