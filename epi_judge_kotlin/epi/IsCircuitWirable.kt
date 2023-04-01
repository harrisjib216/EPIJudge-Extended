package epi

import epi.test_framework.EpiTest

object IsCircuitWirable {
    fun isAnyPlacementFeasible(graph: List<GraphVertex?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    @EpiTest(testDataFile = "is_circuit_wirable.tsv")
    @Throws(Exception::class)
    fun isAnyPlacementFeasibleWrapper(executor: TimedExecutor,
                                      k: Int, edges: List<Edge>): Boolean {
        if (k <= 0) throw RuntimeException("Invalid k value")
        val graph: List<GraphVertex> = ArrayList()
        for (i in 0 until k) graph.add(GraphVertex())
        for (e in edges) {
            if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k) throw RuntimeException("Invalid vertex index")
            graph[e.from].edges.add(graph[e.to])
        }
        return executor.run { isAnyPlacementFeasible(graph) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsCircuitWirable.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class GraphVertex {
        var d = -1
        var edges: List<GraphVertex> = ArrayList()
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Edge(var from: Int, var to: Int)
}