package epi.test_framework

import java.util.Collections

class TestTimer {
    private var start: Long = 0
    var microseconds: Long = 0
        private set

    constructor() {}
    constructor(durationSeconds: Long) {
        val SECOND_TO_MICRO: Long = 1000000
        microseconds = durationSeconds * SECOND_TO_MICRO
    }

    fun start() {
        start = System.nanoTime()
    }

    fun stop() {
        val NANO_TO_MICRO = 0.001
        microseconds += ((System.nanoTime() - start) * NANO_TO_MICRO) as Long
    }

    companion object {
        fun durationToString(dur: Long): String {
            val MILLI_TO_MICRO: Long = 1000
            val SECOND_TO_MICRO = MILLI_TO_MICRO * 1000
            val DURATION_FORMAT = "%4d"
            return if (dur == 0L) {
                "  <1 us"
            } else if (dur < MILLI_TO_MICRO) {
                String.format("$DURATION_FORMAT us", dur)
            } else if (dur < SECOND_TO_MICRO) {
                String.format("$DURATION_FORMAT ms", dur / MILLI_TO_MICRO)
            } else {
                String.format("$DURATION_FORMAT  s", dur / SECOND_TO_MICRO)
            }
        }

        fun avgAndMedianFromDuration(durations: List<Long>): LongArray {
            Collections.sort(durations)
            val avg: Long = durations.stream().mapToLong(Long::longValue).sum() / durations.size()
            val median = if (durations.size() % 2 === 1) durations[durations.size() / 2] else (durations[durations.size() / 2 - 1] +
                    durations[durations.size() / 2]) /
                    2
            return longArrayOf(avg, median)
        }
    }
}