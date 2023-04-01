package epi.test_framework.serialization_traits

import epi.test_framework.TestUtils

class FloatTrait : SerializationTrait() {
    @Override
    override fun name(): String {
        return "float"
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        return jsonObject.asFloat()
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(argName)
    }

    @Override
    override fun getMetrics(x: Object): List<Integer> {
        if (x is Float) {
            val `val`: Float = Math.abs(x as Float)
            return Collections.singletonList(Math.min(`val`, Integer.MAX_VALUE) as Int)
        }
        throw RuntimeException("Expected Float")
    }

    @Override
    override fun argumentsEqual(a: Object?, b: Object?): Boolean {
        if (a is Float && b is Float) {
            return TestUtils.floatComparison(a as Float?, b as Float?)
        }
        throw RuntimeException("Expected Float")
    }
}