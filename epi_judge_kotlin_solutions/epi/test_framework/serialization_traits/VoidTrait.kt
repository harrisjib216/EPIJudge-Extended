package epi.test_framework.serialization_traits

import epi.test_framework.minimal_json.JsonValue

class VoidTrait : SerializationTrait() {
    @Override
    override fun name(): String {
        return "void"
    }

    @Override
    override fun parse(jsonObject: JsonValue?): Object {
        throw RuntimeException("Can't parse void")
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.emptyList()
    }

    @Override
    override fun getMetrics(x: Object?): List<Integer> {
        return Collections.emptyList()
    }

    @get:Override
    override val isVoid: Boolean
        get() = true
}