package epi

import epi.test_framework.EpiTest

object DeadlockDetection {
    fun isDeadlocked(graph: List<GraphVertex?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    @EpiTest(testDataFile = "deadlock_detection.tsv")
    @Throws(Exception::class)
    fun isDeadlockedWrapper(executor: TimedExecutor,
                            numNodes: Int, edges: List<Edge>): Boolean {
        if (numNodes <= 0) {
            throw RuntimeException("Invalid numNodes value")
        }
        val graph: List<GraphVertex> = ArrayList()
        for (i in 0 until numNodes) {
            graph.add(GraphVertex())
        }
        for (e in edges) {
            if (e.from < 0 || e.from >= numNodes || e.to < 0 || e.to >= numNodes) {
                throw RuntimeException("Invalid vertex index")
            }
            graph[e.from].edges.add(graph[e.to])
        }
        return executor.run { isDeadlocked(graph) }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeadlockDetection.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class GraphVertex {
        var edges: List<GraphVertex>

        init {
            edges = ArrayList()
        }
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Edge(var from: Int, var to: Int)
}