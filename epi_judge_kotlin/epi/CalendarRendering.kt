package epi

import epi.test_framework.EpiTest

object CalendarRendering {
    @EpiTest(testDataFile = "calendar_rendering.tsv")
    fun findMaxSimultaneousEvents(A: List<Event?>?): Int {
        // TODO - you fill in here.
        return 0
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