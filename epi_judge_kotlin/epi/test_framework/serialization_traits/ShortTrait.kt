package epi.test_framework.serialization_traits

import epi.test_framework.minimal_json.JsonValue

class ShortTrait : SerializationTrait() {
    @Override
    override fun name(): String {
        return "int"
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        return jsonObject.asInt()
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(argName)
    }

    @Override
    override fun getMetrics(x: Object): List<Integer> {
        if (x is Short) {
            return Collections.singletonList(Math.abs(x as Int))
        }
        throw RuntimeException("Expected Short")
    }
}