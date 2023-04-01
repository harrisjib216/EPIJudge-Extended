package epi

import epi.test_framework.EpiTest

object MatrixRotation {
    fun rotateMatrix(squareMatrix: List<List<Integer>>) {
        val matrixSize: Int = squareMatrix.size() - 1
        for (i in 0 until squareMatrix.size() / 2) {
            for (j in i until matrixSize - i) {
                // Perform a 4-way exchange.
                val temp1: Int = squareMatrix[matrixSize - j][i]
                val temp2: Int = squareMatrix[matrixSize - i][matrixSize - j]
                val temp3: Int = squareMatrix[j][matrixSize - i]
                val temp4: Int = squareMatrix[i][j]
                squareMatrix[i].set(j, temp1)
                squareMatrix[matrixSize - j].set(i, temp2)
                squareMatrix[matrixSize - i].set(matrixSize - j, temp3)
                squareMatrix[j].set(matrixSize - i, temp4)
            }
        }
    }

    @EpiTest(testDataFile = "matrix_rotation.tsv")
    fun rotateMatrixWrapper(squareMatrix: List<List<Integer>>): List<List<Integer>> {
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