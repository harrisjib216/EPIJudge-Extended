package epi

import epi.test_framework.EpiTest

object SearchForMissingElement {
    @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")
    fun findDuplicateMissing(A: List<Integer>): DuplicateAndMissing {

        // Compute the XOR of all numbers from 0 to |A| - 1 and all entries in A.
        var missXorDup = 0
        for (i in 0 until A.size()) {
            missXorDup = missXorDup xor (i xor A[i])
        }

        // We need to find a bit that's set to 1 in missXorDup. Such a bit
        // must exist if there is a single missing number and a single duplicated
        // number in A.
        //
        // The bit-fiddling assignment below sets all of bits in differBit to 0
        // except for the least significant bit in missXorDup that's 1.
        val differBit = missXorDup and (missXorDup - 1).inv()
        var missOrDup = 0
        for (i in 0 until A.size()) {
            // Focus on entries and numbers in which the differBit-th bit is 1.
            if (i and differBit != 0) {
                missOrDup = missOrDup xor i
            }
            if (A[i] and differBit !== 0) {
                missOrDup = missOrDup xor A[i]
            }
        }

        // missOrDup is either the missing value or the duplicated entry. If
        // missOrDup is in A, missOrDup is the duplicate; otherwise, missOrDup is
        // the missing value.
        return if (A.contains(missOrDup)) DuplicateAndMissing(missOrDup, missOrDup xor missXorDup) else DuplicateAndMissing(missOrDup xor missXorDup, missOrDup)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchForMissingElement.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Integer::class, Integer::class])
    class DuplicateAndMissing(duplicate: Integer, missing: Integer) {
        var duplicate: Integer
        var missing: Integer

        init {
            this.duplicate = duplicate
            this.missing = missing
        }

        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val that = o as DuplicateAndMissing
            return if (!duplicate.equals(that.duplicate)) {
                false
            } else missing.equals(that.missing)
        }

        @Override
        override fun hashCode(): Int {
            var result: Int = duplicate.hashCode()
            result = 31 * result + missing.hashCode()
            return result
        }

        @Override
        override fun toString(): String {
            return "duplicate: $duplicate, missing: $missing"
        }
    }
}