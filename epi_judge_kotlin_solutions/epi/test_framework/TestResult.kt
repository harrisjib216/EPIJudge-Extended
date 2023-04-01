package epi.test_framework

import kotlin.Throws
import kotlin.jvm.JvmOverloads

enum class TestResult {
    PASSED, FAILED, TIMEOUT, UNKNOWN_EXCEPTION, STACK_OVERFLOW, RUNTIME_EXCEPTION
}