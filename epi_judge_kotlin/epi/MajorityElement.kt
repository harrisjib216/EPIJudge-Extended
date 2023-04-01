package epi

import epi.test_framework.EpiTest

object MajorityElement {
    fun majoritySearch(stream: Iterator<String?>?): String {
        // TODO - you fill in here.
        return ""
    }

    @EpiTest(testDataFile = "majority_element.tsv")
    fun majoritySearchWrapper(stream: List<String?>): String {
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