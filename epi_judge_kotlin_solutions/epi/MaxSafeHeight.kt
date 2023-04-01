package epi

import epi.test_framework.EpiTest

object MaxSafeHeight {
    @EpiTest(testDataFile = "max_safe_height.tsv")
    fun getHeight(cases: Int, drops: Int): Int {
        val F: List<List<Integer>> = ArrayList(cases + 1)
        for (i in 0 until cases + 1) {
            F.add(ArrayList(Collections.nCopies(drops + 1, -1)))
        }
        return getHeightHelper(cases, drops, F)
    }

    private fun getHeightHelper(cases: Int, drops: Int,
                                F: List<List<Integer>>): Int {
        if (cases == 0 || drops == 0) {
            return 0
        } else if (cases == 1) {
            return drops
        }
        if (F[cases][drops] === -1) {
            F[cases].set(drops, getHeightHelper(cases, drops - 1, F) +
                    getHeightHelper(cases - 1, drops - 1, F) + 1)
        }
        return F[cases][drops]
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxSafeHeight.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}