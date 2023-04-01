package epi

import epi.test_framework.EpiTest

object AbsentValueArray {
    fun findMissingElement(stream: Iterable<Integer>): Int {
        val NUM_BUCKET = 1 shl 16
        val counter = IntArray(NUM_BUCKET)
        var s: Iterator<Integer> = stream.iterator()
        while (s.hasNext()) {
            val idx: Int = s.next() ushr 16
            ++counter[idx]
        }

        // Look for a bucket that contains less than (1 << 16) elements.
        val BUCKET_CAPACITY = 1 shl 16
        var candidateBucket = 0
        for (i in 0 until NUM_BUCKET) {
            if (counter[i] < BUCKET_CAPACITY) {
                candidateBucket = i
                break
            }
        }

        // Finds all IP addresses in the stream whose first 16 bits
        // are equal to candidateBucket.
        val candidates = BitSet(BUCKET_CAPACITY)
        s = stream.iterator() // Search from the beginning again.
        while (s.hasNext()) {
            val x: Int = s.next()
            val upperPartX = x ushr 16
            if (candidateBucket == upperPartX) {
                // Records the presence of 16 LSB of x.
                val lowerPartX = (1 shl 16) - 1 and x
                candidates.set(lowerPartX)
            }
        }
        for (i in 0 until BUCKET_CAPACITY) {
            if (!candidates.get(i)) {
                return candidateBucket shl 16 or i
            }
        }
        throw IllegalArgumentException("no missing element")
    }

    @EpiTest(testDataFile = "absent_value_array.tsv")
    @Throws(Exception::class)
    fun findMissingElementWrapper(stream: List<Integer>) {
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