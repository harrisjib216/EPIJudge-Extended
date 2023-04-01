package epi.test_framework

import java.util.ArrayList

/**
 * Tested function is allowed to throw this exception
 * in order to mark the current test as failed.
 */
class TestFailure : Exception {
    var description: String
        private set
    private var properties: List<Property>

    enum class PropertyName {
        EXCEPTION_MESSAGE,  // message of unhandled exception
        EXPLANATION,  // explanation from TSV file
        COMMAND,  // last command, that caused the error, in API-testing programs
        STATE,  // state of the user-defined collection (for instance, in API

        // testing)
        EXPECTED,  // expected result
        RESULT,  // user-produced result
        MISSING_ITEMS,  // list of items from input that are missing in the result

        // set
        EXCESS_ITEMS,  // list of items from result that are missing in the input

        // set
        MISMATCH_INDEX,  // for collections: index of the wrong item in result

        // for binary trees: instance of TreePath describing the
        // position of the wrong item
        EXPECTED_ITEM,  // value of the expected item in collection (not the whole

        // collection)
        RESULT_ITEM // value of the result item in collection (not the whole
        // collection)
    }

    inner class Property(private val name: PropertyName, value: Object) {
        private val value: Object

        init {
            this.value = value
        }

        fun name(): String {
            return name.toString().toLowerCase().replace('_', ' ')
        }

        fun rawName(): PropertyName {
            return name
        }

        fun id(): Int {
            return name.ordinal()
        }

        fun value(): Object {
            return value
        }
    }

    constructor() {
        properties = ArrayList()
        description = ""
    }

    constructor(description: String) {
        properties = ArrayList()
        this.description = description
    }

    fun withProperty(name: PropertyName?, value: Object?): TestFailure {
        properties.add(Property(name, value))
        return this
    }

    fun withMismatchInfo(mismatchIndex: Object?, expectedItem: Object?,
                         resultItem: Object?): TestFailure {
        return withProperty(PropertyName.MISMATCH_INDEX, mismatchIndex)
                .withProperty(PropertyName.EXPECTED_ITEM, expectedItem)
                .withProperty(PropertyName.RESULT_ITEM, resultItem)
    }

    val maxPropertyNameLength: Int
        get() = properties.stream()
                .mapToInt { property -> property.name().length() }
                .max()
                .orElse(0)

    fun getProperties(): List<Property> {
        properties.sort(Comparator.comparingInt { obj: Property -> obj.id() })
        return properties
    }
}