package epi

import epi.test_framework.EpiTest

object SearchMaze {
    fun searchMaze(maze: List<List<Color?>?>?,
                   s: Coordinate?, e: Coordinate?): List<Coordinate> {
        // TODO - you fill in here.
        return Collections.emptyList()
    }

    fun pathElementIsFeasible(maze: List<List<Integer>>,
                              prev: Coordinate, cur: Coordinate): Boolean {
        return if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y && cur.y < maze[cur.x].size() && maze[cur.x][cur.y] === 0)) {
            false
        } else cur.x == prev.x + 1 && cur.y == prev.y || cur.x == prev.x - 1 && cur.y == prev.y || cur.x == prev.x && cur.y == prev.y + 1 || cur.x == prev.x && cur.y == prev.y - 1
    }

    @EpiTest(testDataFile = "search_maze.tsv")
    @Throws(TestFailure::class)
    fun searchMazeWrapper(maze: List<List<Integer>>,
                          s: Coordinate, e: Coordinate?): Boolean {
        val colored: List<List<Color>> = ArrayList()
        for (col in maze) {
            val tmp: List<Color> = ArrayList()
            for (i in col) {
                tmp.add(if (i == 0) Color.WHITE else Color.BLACK)
            }
            colored.add(tmp)
        }
        val path = searchMaze(colored, s, e)
        if (path.isEmpty()) {
            return s.equals(e)
        }
        if (!path[0].equals(s) || !path[path.size() - 1].equals(e)) {
            throw TestFailure("Path doesn't lay between start and end points")
        }
        for (i in 1 until path.size()) {
            if (!pathElementIsFeasible(maze, path[i - 1], path[i])) {
                throw TestFailure("Path contains invalid segments")
            }
        }
        return true
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchMaze.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Coordinate(var x: Int, var y: Int) {
        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val that = o as Coordinate
            return if (x != that.x || y != that.y) {
                false
            } else true
        }
    }

    enum class Color {
        WHITE, BLACK
    }
}