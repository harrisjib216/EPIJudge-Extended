package epi

import epi.test_framework.EpiTest

object MatrixConnectedRegions {
    fun flipColor(x: Int, y: Int, image: List<List<Boolean>>) {
        val color = image[x][y]
        val q: Queue<Coordinate> = ArrayDeque()
        image[x].set(y, !image[x][y]) // Flips.
        q.add(Coordinate(x, y))
        while (!q.isEmpty()) {
            val cur: Coordinate = q.element()
            for (nextMove in List.of(Coordinate(cur.x, cur.y + 1),
                    Coordinate(cur.x, cur.y - 1),
                    Coordinate(cur.x + 1, cur.y),
                    Coordinate(cur.x - 1, cur.y))) {
                if (nextMove.x >= 0 && nextMove.x < image.size() && nextMove.y >= 0 && nextMove.y < image[nextMove.x].size() && image[nextMove.x][nextMove.y] === color) {
                    // Flips the color.
                    image[nextMove.x].set(nextMove.y, !color)
                    q.add(nextMove)
                }
            }
            q.remove()
        }
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

    private class Coordinate(x: Integer, y: Integer) {
        var x: Integer
        var y: Integer

        init {
            this.x = x
            this.y = y
        }
    }
}