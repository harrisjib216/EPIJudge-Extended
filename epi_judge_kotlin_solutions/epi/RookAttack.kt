package epi

import epi.test_framework.EpiTest

object RookAttack {
    fun rookAttack(A: List<List<Integer>>) {
        val m: Int = A.size()
        val n: Int = A[0].size()
        val hasFirstRowZero = A[0].contains(0)
        val hasFirstColumnZero: Boolean = A.stream().anyMatch { row -> row.get(0) === 0 }
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (A[i][j] === 0) {
                    A[i].set(0, 0)
                    A[0].set(j, 0)
                }
            }
        }
        for (i in 1 until m) {
            if (A[i][0] === 0) {
                Collections.fill(A[i], 0)
            }
        }
        for (j in 1 until n) {
            if (A[0][j] === 0) {
                val idx: Int = j
                A.stream().forEach { row -> row.set(idx, 0) }
            }
        }
        if (hasFirstRowZero) {
            Collections.fill(A[0], 0)
        }
        if (hasFirstColumnZero) {
            A.stream().forEach { row -> row.set(0, 0) }
        }
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