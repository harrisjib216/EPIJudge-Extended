package epi.test_framework.minimal_json

import kotlin.Throws
import kotlin.jvm.Transient
import kotlin.jvm.Synchronized

/**
 * Represents a member of a JSON object, a pair of a name and a value.
 */
class Member internal constructor(
        /**
         * Returns the name of this member.
         *
         * @return the name of this member, never `null`
         */
        val name: String, value: JsonValue) {
    private val value: JsonValue

    init {
        this.value = value
    }

    /**
     * Returns the value of this member.
     *
     * @return the value of this member, never `null`
     */
    fun getValue(): JsonValue {
        return value
    }

    @Override
    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + name.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

    /**
     * Indicates whether a given object is "equal to" this JsonObject. An object
     * is considered equal
     * if it is also a `JsonObject` and both objects contain the same
     * members *in
     * the same order*.
     *
     *
     * If two JsonObjects are equal, they will also produce the same JSON output.
     *
     *
     * @param object
     * the object to be compared with this JsonObject
     * @return <tt>true</tt> if the specified object is equal to this JsonObject,
     * `false`
     * otherwise
     */
    @Override
    override fun equals(`object`: Object?): Boolean {
        if (this === `object`) {
            return true
        }
        if (`object` == null) {
            return false
        }
        if (getClass() !== `object`.getClass()) {
            return false
        }
        val other = `object` as Member
        return name.equals(other.name) && value.equals(other.value)
    }
}