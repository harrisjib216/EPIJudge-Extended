package epi

import epi.test_framework.EpiTest

object MatrixConnectedRegions {
    fun flipColor(x: Int, y: Int, image: List<List<Boolean?>?>?) {
        // TODO - you fill in here.
        return
    }

    @EpiTest(testDataFile = "painting.tsv")
    @Throws(Exception::class)
    fun flipColorWrapper(executor: TimedExecutor,
                         x: Int, y: Int,
                         image: List<List<Integer>>): List<List<Integer>> {
        var image: List<List<Integer>> = image
        val B: List<List<Boolean>> = ArrayList()
        for (i in 0 until image.size()) {
            B.add(ArrayList())
            for (j in 0 until image[i].size()) {
                B[i].add(image[i][j] === 1)
            }
        }
        executor.run { flipColor(x, y, B) }
        image = ArrayList()
        for (i in 0 until B.size()) {
            image.add(ArrayList())
            for (j in 0 until B[i].size()) {
                image[i].add(if (B[i][j]) 1 else 0)
            }
        }
        return image
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}