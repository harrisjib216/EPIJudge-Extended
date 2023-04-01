package epi.test_framework.serialization_traits

import epi.test_framework.minimal_json.JsonValue

class CharacterTrait : SerializationTrait() {
    @Override
    override fun name(): String {
        return "string"
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        val arg: String = jsonObject.asString()
        if (arg.length() !== 1) {
            throw RuntimeException(
                    "Character parser: string must contain exactly 1 char")
        }
        return arg.charAt(0)
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(argName)
    }

    @Override
    override fun getMetrics(x: Object): List<Integer> {
        if (x is Character) {
            return Collections.singletonList(x as Character as Int)
        }
        throw RuntimeException("Expected Character")
    }
}