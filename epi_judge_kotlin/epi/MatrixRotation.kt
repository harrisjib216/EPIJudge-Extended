package epi

import epi.test_framework.EpiTest

object MatrixRotation {
    fun rotateMatrix(squareMatrix: List<List<Integer?>?>?) {
        // TODO - you fill in here.
        return
    }

    @EpiTest(testDataFile = "matrix_rotation.tsv")
    fun rotateMatrixWrapper(squareMatrix: List<List<Integer?>?>?): List<List<Integer?>?>? {
        rotateMatrix(squareMatrix)
        return squareMatrix
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MatrixRotation.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}