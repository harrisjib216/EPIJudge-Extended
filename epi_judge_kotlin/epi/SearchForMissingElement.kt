package epi

import epi.test_framework.EpiTest

object SearchForMissingElement {
    @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")
    fun findDuplicateMissing(A: List<Integer?>?): DuplicateAndMissing {
        // TODO - you fill in here.
        return DuplicateAndMissing(0, 0)
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