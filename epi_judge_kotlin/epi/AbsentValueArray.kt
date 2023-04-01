package epi

import epi.test_framework.EpiTest

object AbsentValueArray {
    fun findMissingElement(stream: Iterable<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    @EpiTest(testDataFile = "absent_value_array.tsv")
    @Throws(Exception::class)
    fun findMissingElementWrapper(stream: List<Integer?>) {
        try {
            val res = findMissingElement(stream)
            if (stream.stream().filter { a -> a.equals(res) }.findFirst().isPresent()) {
                throw TestFailure(String.valueOf(res) + " appears in stream")
            }
        } catch (e: IllegalArgumentException) {
            throw TestFailure("Unexpected no missing element exception")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AbsentValueArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}