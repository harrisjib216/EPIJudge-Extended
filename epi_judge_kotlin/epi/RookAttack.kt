package epi

import epi.test_framework.EpiTest

object RookAttack {
    fun rookAttack(A: List<List<Integer?>?>?) {
        // TODO - you fill in here.
        return
    }

    @EpiTest(testDataFile = "rook_attack.tsv")
    fun rookAttackWrapper(A: List<List<Integer?>?>?): List<List<Integer>> {
        val aCopy: List<List<Integer>> = ArrayList(A)
        rookAttack(aCopy)
        return aCopy
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RookAttack.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}