package epi.test_framework.serialization_traits

import epi.test_framework.minimal_json.JsonValue

class SetTrait(innerTypeTrait: SerializationTrait) : SerializationTrait() {
    private val listTrait: ListTrait

    init {
        listTrait = ListTrait(innerTypeTrait)
    }

    @Override
    override fun name(): String {
        return String.format("set(%s)", listTrait.getInnerTrait().name())
    }

    @Override
    override fun parse(jsonObject: JsonValue): HashSet<Object> {
        return HashSet(listTrait.parse(jsonObject))
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(String.format("size(%s)", argName))
    }

    @Override
    override fun getMetrics(x: Object): List<Integer> {
        if (x is Set) {
            return Collections.singletonList((x as Set).size())
        }
        throw RuntimeException("Expected Set")
    }

    // TODO: Custom parser that throws TestFailure with mismatch info
    val innerTrait: epi.test_framework.serialization_traits.SerializationTrait
        get() = listTrait.getInnerTrait()
}