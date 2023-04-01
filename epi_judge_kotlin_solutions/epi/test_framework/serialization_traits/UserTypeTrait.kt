package epi.test_framework.serialization_traits

import epi.test_framework.EpiUserType

class UserTypeTrait(userType: Class<*>, userTypeInfo: EpiUserType) : SerializationTrait() {
    private val typeInfo: EpiUserType
    private val ctorParamTraits: List<SerializationTrait>
    private var ctor: Constructor<*>? = null

    init {
        typeInfo = userTypeInfo
        ctorParamTraits = Arrays.stream(userTypeInfo.ctorParams())
                .map(TraitsFactory::getTrait)
                .collect(Collectors.toList())
        ctor = try {
            userType.getDeclaredConstructor(userTypeInfo.ctorParams())
        } catch (e: NoSuchMethodException) {
            throw RuntimeException(String.format(
                    "%s does not have a constructor with signature %s",
                    userType.getTypeName(), Arrays.toString(userTypeInfo.ctorParams())))
        }
    }

    @Override
    override fun name(): String {
        val sb = StringBuilder()
        sb.append("tuple(")
        var first = true
        for (t in ctorParamTraits) {
            if (first) {
                first = false
            } else {
                sb.append(",")
            }
            sb.append(t.name())
        }
        sb.append(")")
        return sb.toString()
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        val a: JsonArray = jsonObject.asArray()
        if (a.size() !== ctorParamTraits.size()) {
            throw RuntimeException(
                    String.format("Tuple parser: expected %d values, provided %d",
                            ctorParamTraits.size(), a.size()))
        }
        val params: Array<Object?> = arrayOfNulls<Object>(ctorParamTraits.size())
        for (i in 0 until ctorParamTraits.size()) {
            params[i] = ctorParamTraits[i].parse(a.get(i))
        }
        return try {
            ctor.newInstance(params)
        } catch (e: InstantiationException) {
            throw RuntimeException("Tuple parser: " + e.getMessage())
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Tuple parser: " + e.getMessage())
        } catch (e: InvocationTargetException) {
            throw RuntimeException("Tuple parser: " + e.getMessage())
        }
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        // TODO: Find how to provide custom metrics.
        return Collections.emptyList()
    }

    @Override
    override fun getMetrics(x: Object?): List<Integer> {
        return Collections.emptyList()
    }
}