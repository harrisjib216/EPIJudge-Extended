package epi

import epi.test_framework.EpiTest

object MajorityElement {
    fun majoritySearch(stream: Iterator<String>): String {
        var candidate = ""
        var candidateCount = 0
        while (stream.hasNext()) {
            val it = stream.next()
            if (candidateCount == 0) {
                candidate = it
                candidateCount = 1
            } else if (candidate.equals(it)) {
                ++candidateCount
            } else {
                --candidateCount
            }
        }
        return candidate
    }

    @EpiTest(testDataFile = "majority_element.tsv")
    fun majoritySearchWrapper(stream: List<String>): String {
        return majoritySearch(stream.iterator())
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MajorityElement.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}