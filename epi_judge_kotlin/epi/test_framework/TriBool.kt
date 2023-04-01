package epi.test_framework

import kotlin.Throws
import kotlin.jvm.JvmOverloads

enum class TriBool {
    FALSE, TRUE, INDETERMINATE;

    fun getOrDefault(defaultValue: Boolean): Boolean {
        return when (this) {
            FALSE -> false
            TRUE -> true
            INDETERMINATE -> defaultValue
            else -> defaultValue
        }
    }
}