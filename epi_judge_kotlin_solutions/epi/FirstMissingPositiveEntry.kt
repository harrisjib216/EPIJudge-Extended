package epi

import epi.test_framework.EpiTest

object FirstMissingPositiveEntry {
    @EpiTest(testDataFile = "first_missing_positive_entry.tsv")
    fun findFirstMissingPositive(A: List<Integer>): Int {

        // Record which values are present by writing A.get(i) to index A.get(i) - 1
        // if A.get(i) is between 1 and A.size(), inclusive. We save the value at
        // index A.get(i) - 1 by swapping it with the entry at i. If A.get(i) is
        // negative or greater than n, we just advance i.
        for (i in 0 until A.size()) {
            while (0 < A[i] && A[i] <= A.size() &&
                    !A[A[i] - 1].equals(A[i])) {
                Collections.swap(A, i, A[i] - 1)
            }
        }

        // Second pass through A to search for the first index i such that A.get(i)
        // != i + 1, indicating that i + 1 is absent. If all numbers between 1 and
        // A.size() are present, the smallest missing positive is A.size() + 1.
        return IntStream.range(0, A.size())
                .filter { i -> A[i] !== i + 1 }
                .findFirst()
                .orElse(A.size()) +
                1
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "FirstMissingPositiveEntry.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}