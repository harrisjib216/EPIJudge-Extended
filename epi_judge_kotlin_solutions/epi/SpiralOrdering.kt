package epi

import epi.test_framework.EpiTest

object SpiralOrdering {
    @EpiTest(testDataFile = "spiral_ordering.tsv")
    fun matrixInSpiralOrder(squareMatrix: List<List<Integer>>): List<Integer> {
        val spiralOrdering: List<Integer> = ArrayList()
        for (offset in 0 until Math.ceil(0.5 * squareMatrix.size())) {
            matrixLayerInClockwise(squareMatrix, offset, spiralOrdering)
        }
        return spiralOrdering
    }

    private fun matrixLayerInClockwise(squareMatrix: List<List<Integer>>,
                                       offset: Int,
                                       spiralOrdering: List<Integer>) {
        if (offset == squareMatrix.size() - offset - 1) {
            // squareMatrix has odd dimension, and we are at its center.
            spiralOrdering.add(squareMatrix[offset][offset])
            return
        }
        for (j in offset until squareMatrix.size() - offset - 1) {
            spiralOrdering.add(squareMatrix[offset][j])
        }
        for (i in offset until squareMatrix.size() - offset - 1) {
            spiralOrdering.add(
                    squareMatrix[i][squareMatrix.size() - offset - 1])
        }
        for (j in squareMatrix.size() - offset - 1 downTo offset + 1) {
            spiralOrdering.add(
                    squareMatrix[squareMatrix.size() - offset - 1][j])
        }
        for (i in squareMatrix.size() - offset - 1 downTo offset + 1) {
            spiralOrdering.add(squareMatrix[i][offset])
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpiralOrdering.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}