package epi

import epi.test_framework.EpiTest

object MaxTeamsInPhotograph {
    fun findLargestNumberTeams(graph: List<GraphVertex>): Int {
        var maxLevel = 0
        for (g in graph) {
            if (g.maxDistance == 0) {
                maxLevel = Math.max(maxLevel, dfs(g))
            }
        }
        return maxLevel
    }

    private fun dfs(curr: GraphVertex): Int {
        curr.maxDistance = 1
        for (vertex in curr.edges) {
            curr.maxDistance = Math.max(
                    curr.maxDistance,
                    (if (vertex.maxDistance != 0) vertex.maxDistance else dfs(vertex)) + 1)
        }
        return curr.maxDistance
    }

    @EpiTest(testDataFile = "max_teams_in_photograph.tsv")
    @Throws(Exception::class)
    fun findLargestNumberTeamsWrapper(executor: TimedExecutor, k: Int,
                                      edges: List<Edge>): Int {
        if (k <= 0) {
            throw RuntimeException("Invalid k value")
        }
        val graph: List<GraphVertex> = ArrayList()
        for (i in 0 until k) {
            graph.add(GraphVertex())
        }
        for (e in edges) {
            if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k) {
                throw RuntimeException("Invalid vertex index")
            }
            graph[e.from].edges.add(graph[e.to])
        }
        return executor.run { findLargestNumberTeams(graph) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxTeamsInPhotograph.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class GraphVertex {
        var edges: List<GraphVertex> = ArrayList()

        // Set maxDistance = 0 to indicate unvisited vertex.
        var maxDistance = 0
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Edge(var from: Int, var to: Int)
}