package epi

import epi.test_framework.EpiTest

object MatrixEnclosedRegions {
    fun fillSurroundedRegions(board: List<List<Character>>) {

        // Identifies the regions that are reachable via white path starting from
        // the first or last columns.
        for (i in 0 until board.size()) {
            markBoundaryRegion(i,  /*j=*/0, board)
            markBoundaryRegion(i, board[i].size() - 1, board)
        }
        // Identifies the regions that are reachable via white path starting from
        // the first or last rows.
        for (j in 0 until board[0].size()) {
            markBoundaryRegion( /*i=*/0, j, board)
            markBoundaryRegion(board.size() - 1, j, board)
        }

        // Marks the surrounded white regions as black.
        for (i in 0 until board.size()) {
            for (j in 0 until board[i].size()) {
                board[i].set(j, if (board[i][j] !== 'T') 'B' else 'W')
            }
        }
    }

    private fun markBoundaryRegion(i: Int, j: Int,
                                   board: List<List<Character>>) {
        val q: Queue<Coordinate> = ArrayDeque()
        q.add(Coordinate(i, j))
        // Uses BFS to traverse this region.
        while (!q.isEmpty()) {
            val curr: Coordinate = q.poll()
            if (curr.x >= 0 && curr.x < board.size() && curr.y >= 0 && curr.y < board[curr.x].size() && board[curr.x][curr.y] === 'W') {
                board[curr.x].set(curr.y, 'T')
                q.add(Coordinate(curr.x - 1, curr.y))
                q.add(Coordinate(curr.x + 1, curr.y))
                q.add(Coordinate(curr.x, curr.y - 1))
                q.add(Coordinate(curr.x, curr.y + 1))
            }
        }
    }

    @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
    fun fillSurroundedRegionsWrapper(board: List<List<Character>>): List<List<Character>> {
        fillSurroundedRegions(board)
        return board
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MatrixEnclosedRegions.java",
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