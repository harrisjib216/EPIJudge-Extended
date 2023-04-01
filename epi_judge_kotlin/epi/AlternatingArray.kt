package epi

import epi.test_framework.EpiTest

object AlternatingArray {
    fun rearrange(A: List<Integer?>?) {
        // TODO - you fill in here.
        return
    }

    @Throws(TestFailure::class)
    private fun checkOrder(A: List<Integer>) {
        for (i in 0 until A.size()) {
            if (i % 2 != 0) {
                if (A[i] < A[i - 1]) {
                    throw TestFailure()
                            .withProperty(TestFailure.PropertyName.RESULT, A)
                            .withMismatchInfo(
                                    i, String.format("A[%d] <= A[%d]", i - 1, i),
                                    String.format("%d > %d", A[i - 1], A[i]))
                }
                if (i < A.size() - 1) {
                    if (A[i] < A[i + 1]) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.RESULT, A)
                                .withMismatchInfo(
                                        i, String.format("A[%d] >= A[%d]", i, i + 1),
                                        String.format("%d < %d", A[i], A[i + 1]))
                    }
                }
            } else {
                if (i > 0) {
                    if (A[i - 1] < A[i]) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.RESULT, A)
                                .withMismatchInfo(
                                        i, String.format("A[%d] >= A[%d]", i - 1, i),
                                        String.format("%d < %d", A[i - 1], A[i]))
                    }
                }
                if (i < A.size() - 1) {
                    if (A[i + 1] < A[i]) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.RESULT, A)
                                .withMismatchInfo(
                                        i, String.format("A[%d] <= A[%d]", i, i + 1),
                                        String.format("%d > %d", A[i], A[i + 1]))
                    }
                }
            }
        }
    }

    @EpiTest(testDataFile = "alternating_array.tsv")
    @Throws(Exception::class)
    fun rearrangeWrapper(executor: TimedExecutor, A: List<Integer?>?) {
        val result: List<Integer> = ArrayList(A)
        executor.run { rearrange(result) }
        TestUtils.assertAllValuesPresent(A, result)
        checkOrder(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AlternatingArray.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}