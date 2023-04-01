package epi.test_framework.serialization_traits

import epi.test_framework.minimal_json.JsonValue

class StringTrait : SerializationTrait() {
    @Override
    override fun name(): String {
        return "string"
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        return jsonObject.asString()
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(String.format("size(%s)", argName))
    }

    @Override
    override fun getMetrics(x: Object): List<Integer> {
        if (x is String) {
            return Collections.singletonList((x as String).length())
        }
        throw RuntimeException("Expected String")
    } // TODO Custom parser that throws TestFailure with mismatch info
}