package epi

import epi.test_framework.EpiTest

object MaxTeamsInPhotograph {
    fun findLargestNumberTeams(graph: List<GraphVertex?>?): Int {
        // TODO - you fill in here.
        return 0
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