package epi.test_framework.serialization_traits

import epi.test_framework.minimal_json.JsonValue

class LongTrait : SerializationTrait() {
    @Override
    override fun name(): String {
        return "long"
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        return jsonObject.asLong()
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(argName)
    }

    @Override
    override fun getMetrics(x: Object): List<Integer> {
        if (x is Long) {
            val `val`: Long = Math.abs(x as Long)
            return Collections.singletonList(Math.min(`val`, Integer.MAX_VALUE) as Int)
        }
        throw RuntimeException("Expected Long")
    }
}