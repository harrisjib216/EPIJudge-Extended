package epi.test_framework.serialization_traits

import epi.test_framework.minimal_json.JsonValue

class BooleanTrait : SerializationTrait() {
    @Override
    override fun name(): String {
        return "bool"
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        return jsonObject.asBoolean()
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.emptyList()
    }

    @Override
    override fun getMetrics(x: Object?): List<Integer> {
        return if (x is Boolean) {
            Collections.emptyList()
        } else {
            throw RuntimeException("Expected Boolean")
        }
    }
}