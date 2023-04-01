package epi.test_framework.minimal_json

import java.io.IOException

/**
 * Represents a JSON value. This can be a JSON **object**, an
 * ** array**,
 * a **number**, a **string**, or one of the literals
 * **true**, **false**, and **null**.
 *
 *
 * The literals **true**, **false**, and
 * **null** are
 * represented by the constants [.TRUE], [.FALSE], and [ ][.NULL].
 *
 *
 *
 * JSON **objects** and **arrays** are represented by
 * the subtypes
 * [JsonObject] and [JsonArray]. Instances of these types can be
 * created using the
 * public constructors of these classes.
 *
 *
 *
 * Instances that represent JSON **numbers**,
 * **strings** and
 * **boolean** values can be created using the static factory
 * methods
 * [.valueOf], [.valueOf], [.valueOf],
 * etc.
 *
 *
 *
 * In order to find out whether an instance of this class is of a certain type,
 * the methods
 * [.isObject], [.isArray], [.isString], [ ][.isNumber] etc. can be
 * used.
 *
 *
 *
 * If the type of a JSON value is known, the methods [.asObject], [ ][.asArray],
 * [.asString], [.asInt], etc. can be used to get this value
 * directly in the
 * appropriate target type.
 *
 *
 *
 * This class is **not supposed to be extended** by clients.
 *
 */
@SuppressWarnings("serial") // use default serial UID
abstract class JsonValue internal constructor() : Serializable {
    /**
     * Detects whether this value represents a JSON object. If this is the case,
     * this value is an
     * instance of [JsonObject].
     *
     * @return `true` if this value is an instance of JsonObject
     */
    open val isObject: Boolean
        get() = false

    /**
     * Detects whether this value represents a JSON array. If this is the case,
     * this value is an
     * instance of [JsonArray].
     *
     * @return `true` if this value is an instance of JsonArray
     */
    open val isArray: Boolean
        get() = false

    /**
     * Detects whether this value represents a JSON number.
     *
     * @return `true` if this value represents a JSON number
     */
    open val isNumber: Boolean
        get() = false

    /**
     * Detects whether this value represents a JSON string.
     *
     * @return `true` if this value represents a JSON string
     */
    open val isString: Boolean
        get() = false

    /**
     * Detects whether this value represents a boolean value.
     *
     * @return `true` if this value represents either the JSON literal
     * `true` or
     * `false`
     */
    open val isBoolean: Boolean
        get() = false

    /**
     * Detects whether this value represents the JSON literal `true`.
     *
     * @return `true` if this value represents the JSON literal
     * `true`
     */
    open val isTrue: Boolean
        get() = false

    /**
     * Detects whether this value represents the JSON literal `false`.
     *
     * @return `true` if this value represents the JSON literal
     * `false`
     */
    open val isFalse: Boolean
        get() = false

    /**
     * Detects whether this value represents the JSON literal `null`.
     *
     * @return `true` if this value represents the JSON literal
     * `null`
     */
    open val isNull: Boolean
        get() = false

    /**
     * Returns this JSON value as [JsonObject], assuming that this value
     * represents a JSON
     * object. If this is not the case, an exception is thrown.
     *
     * @return a JSONObject for this value
     * @throws UnsupportedOperationException
     * if this value is not a JSON object
     */
    open fun asObject(): JsonObject? {
        throw UnsupportedOperationException("Not an object: " + toString())
    }

    /**
     * Returns this JSON value as [JsonArray], assuming that this value
     * represents a JSON array.
     * If this is not the case, an exception is thrown.
     *
     * @return a JSONArray for this value
     * @throws UnsupportedOperationException
     * if this value is not a JSON array
     */
    open fun asArray(): JsonArray? {
        throw UnsupportedOperationException("Not an array: " + toString())
    }

    /**
     * Returns this JSON value as an `int` value, assuming that this
     * value represents a
     * JSON number that can be interpreted as Java `int`. If this is
     * not the case, an
     * exception is thrown.
     *
     *
     * To be interpreted as Java `int`, the JSON number must neither
     * contain an exponent
     * nor a fraction part. Moreover, the number must be in the
     * `Integer` range.
     *
     *
     * @return this value as `int`
     * @throws UnsupportedOperationException
     * if this value is not a JSON number
     * @throws NumberFormatException
     * if this JSON number can not be interpreted as `int`
     * value
     */
    open fun asInt(): Int {
        throw UnsupportedOperationException("Not a number: " + toString())
    }

    /**
     * Returns this JSON value as a `long` value, assuming that this
     * value represents a
     * JSON number that can be interpreted as Java `long`. If this is
     * not the case, an
     * exception is thrown.
     *
     *
     * To be interpreted as Java `long`, the JSON number must neither
     * contain an exponent
     * nor a fraction part. Moreover, the number must be in the `Long`
     * range.
     *
     *
     * @return this value as `long`
     * @throws UnsupportedOperationException
     * if this value is not a JSON number
     * @throws NumberFormatException
     * if this JSON number can not be interpreted as `long`
     * value
     */
    open fun asLong(): Long {
        throw UnsupportedOperationException("Not a number: " + toString())
    }

    /**
     * Returns this JSON value as a `float` value, assuming that this
     * value represents a
     * JSON number. If this is not the case, an exception is thrown.
     *
     *
     * If the JSON number is out of the `Float` range, [ ][Float.POSITIVE_INFINITY] or
     * [Float.NEGATIVE_INFINITY] is returned.
     *
     *
     * @return this value as `float`
     * @throws UnsupportedOperationException
     * if this value is not a JSON number
     */
    open fun asFloat(): Float {
        throw UnsupportedOperationException("Not a number: " + toString())
    }

    /**
     * Returns this JSON value as a `double` value, assuming that this
     * value represents a
     * JSON number. If this is not the case, an exception is thrown.
     *
     *
     * If the JSON number is out of the `Double` range, [ ][Double.POSITIVE_INFINITY] or
     * [Double.NEGATIVE_INFINITY] is returned.
     *
     *
     * @return this value as `double`
     * @throws UnsupportedOperationException
     * if this value is not a JSON number
     */
    open fun asDouble(): Double {
        throw UnsupportedOperationException("Not a number: " + toString())
    }

    /**
     * Returns this JSON value as String, assuming that this value represents a
     * JSON string. If this
     * is not the case, an exception is thrown.
     *
     * @return the string represented by this value
     * @throws UnsupportedOperationException
     * if this value is not a JSON string
     */
    open fun asString(): String {
        throw UnsupportedOperationException("Not a string: " + toString())
    }

    /**
     * Returns this JSON value as a `boolean` value, assuming that this
     * value is either
     * `true` or `false`. If this is not the case, an
     * exception is thrown.
     *
     * @return this value as `boolean`
     * @throws UnsupportedOperationException
     * if this value is neither `true` or `false`
     */
    open fun asBoolean(): Boolean {
        throw UnsupportedOperationException("Not a boolean: " + toString())
    }

    /**
     * Writes the JSON representation of this value to the given writer in its
     * minimal form, without
     * any additional whitespace.
     *
     *
     * Writing performance can be improved by using a [ ].
     *
     *
     * @param writer
     * the writer to write this value to
     * @throws IOException
     * if an I/O error occurs in the writer
     */
    @Throws(IOException::class)
    fun writeTo(writer: Writer?) {
        writeTo(writer, WriterConfig.MINIMAL)
    }

    /**
     * Writes the JSON representation of this value to the given writer using the
     * given formatting.
     *
     *
     * Writing performance can be improved by using a [ ].
     *
     *
     * @param writer
     * the writer to write this value to
     * @param config
     * a configuration that controls the formatting or `null`
     * for the minimal form
     * @throws IOException
     * if an I/O error occurs in the writer
     */
    @Throws(IOException::class)
    fun writeTo(writer: Writer?, config: WriterConfig?) {
        if (writer == null) {
            throw NullPointerException("writer is null")
        }
        if (config == null) {
            throw NullPointerException("config is null")
        }
        val buffer = WritingBuffer(writer, 128)
        write(config.createWriter(buffer))
        buffer.flush()
    }

    /**
     * Returns the JSON string for this value in its minimal form, without any
     * additional whitespace.
     * The result is guaranteed to be a valid input for the method [ ][.readFrom] and to
     * create a value that is *equal* to this object.
     *
     * @return a JSON string that represents this value
     */
    @Override
    override fun toString(): String {
        return toString(WriterConfig.MINIMAL)
    }

    /**
     * Returns the JSON string for this value using the given formatting.
     *
     * @param config
     * a configuration that controls the formatting or `null`
     * for the minimal form
     * @return a JSON string that represents this value
     */
    fun toString(config: WriterConfig?): String {
        val writer = StringWriter()
        try {
            writeTo(writer, config)
        } catch (exception: IOException) {
            // StringWriter does not throw IOExceptions
            throw RuntimeException(exception)
        }
        return writer.toString()
    }

    /**
     * Indicates whether some other object is "equal to" this one according to the
     * contract specified
     * in [Object.equals].
     *
     *
     * Two JsonValues are considered equal if and only if they represent the same
     * JSON text. As a
     * consequence, two given JsonObjects may be different even though they
     * contain the same set of
     * names with the same values, but in a different order.
     *
     *
     * @param object
     * the reference object with which to compare
     * @return true if this object is the same as the object argument; false
     * otherwise
     */
    @Override
    override fun equals(`object`: Object?): Boolean {
        return super.equals(`object`)
    }

    @Override
    override fun hashCode(): Int {
        return super.hashCode()
    }

    @Throws(IOException::class)
    abstract fun write(writer: JsonWriter?)

    companion object {
        /**
         * Represents the JSON literal `true`.
         */
        @Deprecated
        @Deprecated("Use <code>Json.TRUE</code> instead")
        val TRUE: JsonValue = JsonLiteral("true")

        /**
         * Represents the JSON literal `false`.
         */
        @Deprecated
        @Deprecated("Use <code>Json.FALSE</code> instead")
        val FALSE: JsonValue = JsonLiteral("false")

        /**
         * Represents the JSON literal `null`.
         */
        @Deprecated
        @Deprecated("Use <code>Json.NULL</code> instead")
        val NULL: JsonValue = JsonLiteral("null")

        /**
         * Reads a JSON value from the given reader.
         *
         *
         * Characters are read in chunks and buffered internally, therefore wrapping
         * an existing reader in
         * an additional `BufferedReader` does **not** improve
         * reading
         * performance.
         *
         *
         * @param reader
         * the reader to read the JSON value from
         * @return the JSON value that has been read
         * @throws IOException
         * if an I/O error occurs in the reader
         * @throws ParseException
         * if the input is not valid JSON
         */
        @Deprecated
        @Deprecated("Use {@link Json#parse(Reader)} instead")
        @Throws(IOException::class)
        fun readFrom(reader: Reader?): JsonValue {
            return Json.parse(reader)
        }

        /**
         * Reads a JSON value from the given string.
         *
         * @param text
         * the string that contains the JSON value
         * @return the JSON value that has been read
         * @throws ParseException
         * if the input is not valid JSON
         */
        @Deprecated
        @Deprecated("Use {@link Json#parse(String)} instead")
        fun readFrom(text: String?): JsonValue {
            return Json.parse(text)
        }

        /**
         * Returns a JsonValue instance that represents the given `int`
         * value.
         *
         * @param value
         * the value to get a JSON representation for
         * @return a JSON value that represents the given value
         */
        @Deprecated
        @Deprecated("Use <code>Json.value()</code> instead")
        fun valueOf(value: Int): JsonValue {
            return Json.value(value)
        }

        /**
         * Returns a JsonValue instance that represents the given `long`
         * value.
         *
         * @param value
         * the value to get a JSON representation for
         * @return a JSON value that represents the given value
         */
        @Deprecated
        @Deprecated("Use <code>Json.value()</code> instead")
        fun valueOf(value: Long): JsonValue {
            return Json.value(value)
        }

        /**
         * Returns a JsonValue instance that represents the given `float`
         * value.
         *
         * @param value
         * the value to get a JSON representation for
         * @return a JSON value that represents the given value
         */
        @Deprecated
        @Deprecated("Use <code>Json.value()</code> instead")
        fun valueOf(value: Float): JsonValue {
            return Json.value(value)
        }

        /**
         * Returns a JsonValue instance that represents the given `double`
         * value.
         *
         * @param value
         * the value to get a JSON representation for
         * @return a JSON value that represents the given value
         */
        @Deprecated
        @Deprecated("Use <code>Json.value()</code> instead")
        fun valueOf(value: Double): JsonValue {
            return Json.value(value)
        }

        /**
         * Returns a JsonValue instance that represents the given string.
         *
         * @param string
         * the string to get a JSON representation for
         * @return a JSON value that represents the given string
         */
        @Deprecated
        @Deprecated("Use <code>Json.value()</code> instead")
        fun valueOf(string: String?): JsonValue {
            return Json.value(string)
        }

        /**
         * Returns a JsonValue instance that represents the given `boolean`
         * value.
         *
         * @param value
         * the value to get a JSON representation for
         * @return a JSON value that represents the given value
         */
        @Deprecated
        @Deprecated("Use <code>Json.value()</code> instead")
        fun valueOf(value: Boolean): JsonValue {
            return Json.value(value)
        }
    }
}