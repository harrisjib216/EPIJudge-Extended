package epi

import epi.test_framework.EpiTest

object Arbitrage {
    @EpiTest(testDataFile = "arbitrage.tsv")
    fun isArbitrageExist(graph: List<List<Double>>): Boolean {

        // Transforms each edge in graph.
        for (edgeList in graph) {
            for (i in 0 until edgeList.size()) {
                edgeList.set(i, -Math.log10(edgeList[i]))
            }
        }

        // Uses Bellman-Ford to find negative weight cycle.
        return bellmanFord(graph, 0)
    }

    private fun bellmanFord(graph: List<List<Double>>, source: Int): Boolean {
        val disToSource: List<Double> = ArrayList(Collections.nCopies(graph.size(), Double.MAX_VALUE))
        disToSource.set(source, 0.0)
        for (times in 1 until graph.size()) {
            var haveUpdate = false
            for (i in 0 until graph.size()) {
                for (j in 0 until graph[i].size()) {
                    if (disToSource[i] !== Double.MAX_VALUE &&
                            disToSource[j] > disToSource[i] + graph[i][j]) {
                        haveUpdate = true
                        disToSource.set(j, disToSource[i] + graph[i][j])
                    }
                }
            }

            // No update in this iteration means no negative cycle.
            if (!haveUpdate) {
                return false
            }
        }

        // Detects cycle if there is any further update.
        for (i in 0 until graph.size()) {
            for (j in 0 until graph[i].size()) {
                if (disToSource[i] !== Double.MAX_VALUE &&
                        disToSource[j] > disToSource[i] + graph[i][j]) {
                    return true
                }
            }
        }
        return false
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Arbitrage.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}