package epi.test_framework

import kotlin.Throws
import kotlin.jvm.JvmOverloads

class TimeoutException internal constructor(timeoutSeconds: Long) : Exception() {
    private val timer: TestTimer

    init {
        timer = TestTimer(timeoutSeconds)
    }

    fun getTimer(): TestTimer {
        return timer
    }
}