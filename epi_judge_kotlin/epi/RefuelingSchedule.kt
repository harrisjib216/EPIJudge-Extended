package epi

import epi.test_framework.EpiTest

object RefuelingSchedule {
    private const val MPG = 20

    // gallons[i] is the amount of gas in city i, and distances[i] is the distance
    // city i to the next city.
    fun findAmpleCity(gallons: List<Integer?>?,
                      distances: List<Integer?>?): Int {
        // TODO - you fill in here.
        return 0
    }

    @EpiTest(testDataFile = "refueling_schedule.tsv")
    @Throws(Exception::class)
    fun findAmpleCityWrapper(executor: TimedExecutor,
                             gallons: List<Integer>,
                             distances: List<Integer>) {
        val result: Int = executor.run { findAmpleCity(gallons, distances) }
        val numCities: Int = gallons.size()
        var tank = 0
        for (i in 0 until numCities) {
            val city: Int = (result + i) % numCities
            tank += gallons[city] * MPG - distances[city]
            if (tank < 0) {
                throw TestFailure(String.format("Out of gas on city %d", city))
            }
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RefuelingSchedule.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}