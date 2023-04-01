package epi

import epi.test_framework.EpiTest

object DutchNationalFlag {
    fun dutchFlagPartition(pivotIndex: Int, A: List<Color>) {
        val pivot = A[pivotIndex]

        /**
         * Keep the following invariants during partitioning:
         * bottom group: A.subList(0, smaller).
         * middle group: A.subList(smaller, equal).
         * unclassified group: A.subList(equal, larger).
         * top group: A.subList(larger, A.size()).
         */
        var smaller = 0
        var equal = 0
        var larger: Int = A.size()
        // Keep iterating as long as there is an unclassified element.
        while (equal < larger) {
            // A.get(equal) is the incoming unclassified element.
            if (A[equal].ordinal() < pivot.ordinal()) {
                Collections.swap(A, smaller++, equal++)
            } else if (A[equal].ordinal() === pivot.ordinal()) {
                ++equal
            } else { // A.get(equal) > pivot.
                Collections.swap(A, equal, --larger)
            }
        }
    }

    @EpiTest(testDataFile = "dutch_national_flag.tsv")
    @Throws(Exception::class)
    fun dutchFlagPartitionWrapper(executor: TimedExecutor,
                                  A: List<Integer?>, pivotIdx: Int) {
        val colors: List<Color> = ArrayList()
        val count = IntArray(3)
        val C = Color.values()
        for (i in 0 until A.size()) {
            count[A[i]]++
            colors.add(C[A[i]])
        }
        val pivot = colors[pivotIdx]
        executor.run { dutchFlagPartition(pivotIdx, colors) }
        var i = 0
        while (i < colors.size() && colors[i].ordinal() < pivot.ordinal()) {
            count[colors[i].ordinal()]--
            ++i
        }
        while (i < colors.size() && colors[i].ordinal() === pivot.ordinal()) {
            count[colors[i].ordinal()]--
            ++i
        }
        while (i < colors.size() && colors[i].ordinal() > pivot.ordinal()) {
            count[colors[i].ordinal()]--
            ++i
        }
        if (i != colors.size()) {
            throw TestFailure("Not partitioned after " + Integer.toString(i) +
                    "th element")
        } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
            throw TestFailure("Some elements are missing from original array")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DutchNationalFlag.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    enum class Color {
        RED, WHITE, BLUE
    }
}