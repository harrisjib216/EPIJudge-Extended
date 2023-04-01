package epi

import epi.test_framework.EpiTest

object DeadlockDetection {
    fun isDeadlocked(graph: List<GraphVertex?>): Boolean {
        return graph.stream().anyMatch { vertex -> vertex.color === GraphVertex.Color.WHITE && hasCycle(vertex) }
    }

    private fun hasCycle(cur: GraphVertex): Boolean {
        // Visiting a gray vertex means a cycle.
        if (cur.color == GraphVertex.Color.GRAY) {
            return true
        }
        cur.color = GraphVertex.Color.GRAY // Marks current vertex as a gray one.
        // Traverse the neighbor vertices.
        if (cur.edges.stream().anyMatch { next -> next.color !== GraphVertex.Color.BLACK && hasCycle(next) }) {
            return true
        }
        cur.color = GraphVertex.Color.BLACK // Marks current vertex as black.
        return false
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
        enum class Color {
            WHITE, GRAY, BLACK
        }

        var color: Color
        var edges: List<GraphVertex>

        init {
            color = Color.WHITE
            edges = ArrayList()
        }
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Edge(var from: Int, var to: Int)
}