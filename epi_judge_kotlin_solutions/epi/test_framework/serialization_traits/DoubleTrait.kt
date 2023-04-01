package epi.test_framework.serialization_traits

import epi.test_framework.TestUtils

class DoubleTrait : SerializationTrait() {
    @Override
    override fun name(): String {
        return "float"
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        return jsonObject.asDouble()
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(argName)
    }

    @Override
    override fun getMetrics(x: Object): List<Integer> {
        if (x is Double) {
            val `val`: Double = Math.abs(x as Double)
            return Collections.singletonList(Math.min(`val`, Integer.MAX_VALUE) as Int)
        }
        throw RuntimeException("Expected Double")
    }

    @Override
    override fun argumentsEqual(a: Object?, b: Object?): Boolean {
        if (a is Double && b is Double) {
            return TestUtils.doubleComparison(a as Double?, b as Double?)
        }
        throw RuntimeException("Expected Double")
    }
}