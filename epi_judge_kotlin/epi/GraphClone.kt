package epi

import epi.test_framework.EpiTest

object GraphClone {
    fun cloneGraph(graph: GraphVertex?): GraphVertex {
        // TODO - you fill in here.
        return GraphVertex(0)
    }

    private fun copyLabels(edges: List<GraphVertex>): List<Integer> {
        val labels: List<Integer> = ArrayList()
        for (e in edges) {
            labels.add(e.label)
        }
        return labels
    }

    @Throws(TestFailure::class)
    private fun checkGraph(node: GraphVertex?, graph: List<GraphVertex>) {
        if (node == null) {
            throw TestFailure("Graph was not copied")
        }
        val vertexSet: Set<GraphVertex> = HashSet()
        val q: Queue<GraphVertex> = ArrayDeque()
        q.add(node)
        vertexSet.add(node)
        while (!q.isEmpty()) {
            val vertex: GraphVertex = q.remove()
            if (vertex.label >= graph.size()) {
                throw TestFailure("Invalid vertex label")
            }
            val label1: List<Integer> = copyLabels(vertex.edges)
            val label2: List<Integer> = copyLabels(graph[vertex.label].edges)
            Collections.sort(label1)
            Collections.sort(label2)
            if (!label1.equals(label2)) {
                throw TestFailure("Edges mismatch")
            }
            for (e in vertex.edges) {
                if (!vertexSet.contains(e)) {
                    vertexSet.add(e)
                    q.add(e)
                }
            }
        }
    }

    @EpiTest(testDataFile = "graph_clone.tsv")
    @Throws(TestFailure::class)
    fun cloneGraphTest(k: Int, edges: List<Edge>) {
        if (k <= 0) {
            throw RuntimeException("Invalid k value")
        }
        val graph: List<GraphVertex> = ArrayList()
        for (i in 0 until k) {
            graph.add(GraphVertex(i))
        }
        for (e in edges) {
            if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k) {
                throw RuntimeException("Invalid vertex index")
            }
            graph[e.from].edges.add(graph[e.to])
        }
        val result = cloneGraph(graph[0])
        checkGraph(result, graph)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "GraphClone.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class GraphVertex(var label: Int) {
        var edges: List<GraphVertex>

        init {
            edges = ArrayList()
        }
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Edge(var from: Int, var to: Int)
}