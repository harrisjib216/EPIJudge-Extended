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

    private val players: List<Player>

    init {
        players = height.stream().map { h -> Player(h) }.collect(Collectors.toList())
    }

    companion object {
        // Checks if team0 can be placed in front of team1.
        fun validPlacementExists(team0: Team?, team1: Team?): Boolean {
            // TODO - you fill in here.
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