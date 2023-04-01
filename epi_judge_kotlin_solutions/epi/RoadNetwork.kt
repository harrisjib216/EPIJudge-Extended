package epi

import epi.test_framework.EpiTest

object RoadNetwork {
    @EpiTest(testDataFile = "road_network.tsv")
    fun findBestProposals(H: List<HighwaySection>, P: List<HighwaySection>, n: Int): HighwaySection {

        // graph stores the shortest path distances between all pairs of vertices.
        val graph: List<List<Integer>> = ArrayList(n)
        for (i in 0 until n) {
            graph.add(ArrayList(Collections.nCopies(n, Integer.MAX_VALUE)))
        }
        for (i in 0 until n) {
            graph[i].set(i, 0)
        }
        // Builds an undirected graph graph based on existing highway sections H.
        for (h in H) {
            graph[h.x].set(h.y, h.distance)
            graph[h.y].set(h.x, h.distance)
        }

        // Performs Floyd Warshall to build the shortest path between vertices.
        floydWarshall(graph)

        // Examines each proposal for shorter distance for all pairs.
        var bestDistanceSaving: Int = Integer.MIN_VALUE
        var bestProposal = HighwaySection(-1, -1, 0) // Default.
        for (p in P) {
            var proposalSaving = 0
            for (a in 0 until n) {
                for (b in 0 until n) {
                    val saving: Int = graph[a][b] -
                            Math.min(
                                    graph[a][p.x] + p.distance + graph[p.y][b],
                                    graph[a][p.y] + p.distance + graph[p.x][b])
                    proposalSaving += Math.max(saving, 0)
                }
            }
            if (proposalSaving > bestDistanceSaving) {
                bestDistanceSaving = proposalSaving
                bestProposal = p
            }
        }
        return bestProposal
    }

    private fun floydWarshall(graph: List<List<Integer>>) {
        for (k in 0 until graph.size()) {
            for (i in 0 until graph.size()) {
                for (j in 0 until graph.size()) {
                    if (graph[i][k] !== Integer.MAX_VALUE && graph[k][j] !== Integer.MAX_VALUE && graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i].set(j, graph[i][k] + graph[k][j])
                    }
                }
            }
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RoadNetwork.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class, Int::class])
    class HighwaySection(var x: Int, var y: Int, var distance: Int) {
        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val that = o as HighwaySection
            return x == that.x && y == that.y && distance == that.distance
        }

        @Override
        override fun toString(): String {
            return "[$x, $y, $distance]"
        }
    }
}