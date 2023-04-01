package epi

import epi.test_framework.EpiTest

internal class Team(height: List<Integer?>) {
    private class Player(h: Integer) : Comparable<Player?> {
        var height: Integer

        init {
            height = h
        }

        @Override
        operator fun compareTo(that: Player): Int {
            return Integer.compare(height, that.height)
        }
    }

    private fun sortPlayersByHeight(): List<Player> {
        return players.stream().sorted().collect(Collectors.toList())
    }

    private val players: List<Player>

    init {
        players = height.stream().map { h -> Player(h) }.collect(Collectors.toList())
    }

    companion object {
        // Checks if team0 can be placed in front of team1.
        fun validPlacementExists(team0: Team, team1: Team): Boolean {
            val team0Sorted = team0.sortPlayersByHeight()
            val team1Sorted = team1.sortPlayersByHeight()
            var i = 0
            while (i < team0Sorted.size() && i < team1Sorted.size()) {
                if (team0Sorted[i].compareTo(team1Sorted[i]) >= 0) {
                    return false
                }
                ++i
            }
            return true
        }
    }
}

object IsArrayDominated {
    @EpiTest(testDataFile = "is_array_dominated.tsv")
    @Throws(Exception::class)
    fun validPlacementExistsWrapper(executor: TimedExecutor, team0: List<Integer?>,
                                    team1: List<Integer?>, expected01: Boolean,
                                    expected10: Boolean) {
        val t0 = Team(team0)
        val t1 = Team(team1)
        val result01: Boolean = executor.run { Team.validPlacementExists(t0, t1) }
        val result10: Boolean = executor.run { Team.validPlacementExists(t1, t0) }
        if (result01 != expected01 || result10 != expected10) {
            throw TestFailure("")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsArrayDominated.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}