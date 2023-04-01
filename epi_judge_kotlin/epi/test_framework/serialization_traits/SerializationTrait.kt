package epi.test_framework.serialization_traits

import epi.test_framework.TestFailure

abstract class SerializationTrait {
    abstract fun name(): String?
    abstract fun parse(jsonObject: JsonValue?): Object?
    abstract fun getMetricNames(argName: String?): List<String?>?
    abstract fun getMetrics(x: Object?): List<Integer?>?
    open val isVoid: Boolean
        get() = false

    @Throws(TestFailure::class)
    open fun argumentsEqual(a: Object?, b: Object?): Boolean {
        return Objects.equals(a, b)
    }
}