package epi.test_framework.serialization_traits

import epi.test_framework.minimal_json.JsonValue

class ListTrait(innerTypeTrait: SerializationTrait) : SerializationTrait() {
    private val innerTypeTrait: SerializationTrait

    init {
        this.innerTypeTrait = innerTypeTrait
    }

    @Override
    override fun name(): String {
        return String.format("array(%s)", innerTypeTrait.name())
    }

    @Override
    override fun parse(jsonObject: JsonValue): List<Object> {
        return StreamSupport.stream(jsonObject.asArray().spliterator(), false)
                .map(innerTypeTrait::parse)
                .collect(Collectors.toList())
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(String.format("size(%s)", argName))
    }

    @Override
    override fun getMetrics(x: Object): List<Integer> {
        if (x is List) {
            return Collections.singletonList((x as List).size())
        }
        throw RuntimeException("Expected List")
    }

    // TODO Custom parser that throws TestFailure with mismatch info
    val innerTrait: epi.test_framework.serialization_traits.SerializationTrait
        get() = innerTypeTrait
}