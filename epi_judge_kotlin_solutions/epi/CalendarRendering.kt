package epi

import epi.test_framework.EpiTest

object CalendarRendering {
    @EpiTest(testDataFile = "calendar_rendering.tsv")
    fun findMaxSimultaneousEvents(A: List<Event?>): Int {

        // Builds an array of all endpoints.
        val E: List<Endpoint> = A.stream()
                .map { event ->
                    List.of(Endpoint(event.start, true),
                            Endpoint(event.finish, false))
                }
                .flatMap(List::stream)
                .collect(Collectors.toList())
        // Sorts the endpoint array according to the time, breaking ties
        // by putting start times before end times.
        E.sort { a, b ->
            if (a.time !== b.time) {
                return@sort Integer.compare(a.time, b.time)
            }
            if (a.isStart && !b.isStart) -1 else if (!a.isStart && b.isStart) 1 else 0
        }

        // Track the number of simultaneous events, and record the maximum
        // number of simultaneous events.
        var maxNumSimultaneousEvents = 0
        var numSimultaneousEvents = 0
        for (endpoint in E) {
            if (endpoint.isStart) {
                ++numSimultaneousEvents
                maxNumSimultaneousEvents = Math.max(numSimultaneousEvents, maxNumSimultaneousEvents)
            } else {
                --numSimultaneousEvents
            }
        }
        return maxNumSimultaneousEvents
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CalendarRendering.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class Event(var start: Int, var finish: Int)
    private class Endpoint internal constructor(var time: Int, var isStart: Boolean)
}