package epi.test_framework.serialization_traits

import epi.ListNode

class LinkedListTrait(innerTypeTrait: SerializationTrait) : SerializationTrait() {
    private val listTrait: ListTrait

    init {
        listTrait = ListTrait(innerTypeTrait)
    }

    @Override
    override fun name(): String {
        return String.format("linked_list(%s)", listTrait.getInnerTrait().name())
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object? {
        val parsed: List<Object> = listTrait.parse(jsonObject)
        var head: ListNode<Object?>? = null
        val valuesIt: ListIterator<Object> = parsed.listIterator(parsed.size())
        while (valuesIt.hasPrevious()) {
            head = ListNode(valuesIt.previous(), head)
        }
        return head
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Collections.singletonList(String.format("size(%s)", argName))
    }

    @Override
    override fun getMetrics(x: Object?): List<Integer> {
        if (x == null) {
            return Collections.singletonList(0)
        } else if (x is ListNode) {
            return Collections.singletonList((x as ListNode).size())
        }
        throw RuntimeException("Expected ListNode")
    }

    // TODO Custom parser that throws TestFailure with mismatch info
    val innerTrait: epi.test_framework.serialization_traits.SerializationTrait
        get() = listTrait.getInnerTrait()
}