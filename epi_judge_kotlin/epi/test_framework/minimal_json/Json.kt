package epi.test_framework.minimal_json

import java.io.IOException

/**
 * This class serves as the entry point to the minimal_json API.
 *
 *
 * To **parse** a given JSON input, use the `parse()`
 * methods like in this
 * example:
 *
 * <pre>
 * JsonObject object = Json.parse(string).asObject();
</pre> *
 *
 *
 * To **create** a JSON data structure to be serialized, use the
 * methods
 * `value()`, `array()`, and `object()`. For
 * example, the following
 * snippet will produce the JSON string *{"foo": 23, "bar": true}*:
 *
 * <pre>
 * String string = Json.object().add("foo", 23).add("bar", true).toString();
</pre> *
 *
 *
 * To create a JSON array from a given Java array, you can use one of the
 * `array()`
 * methods with varargs parameters:
 *
 * <pre>
 * String[] names = ...
 * JsonArray array = Json.array(names);
</pre> *
 */
object Json {
    /**
     * Represents the JSON literal `null`.
     */
    val NULL: JsonValue = JsonLiteral("null")

    /**
     * Represents the JSON literal `true`.
     */
    val TRUE: JsonValue = JsonLiteral("true")

    /**
     * Represents the JSON literal `false`.
     */
    val FALSE: JsonValue = JsonLiteral("false")

    /**
     * Returns a JsonValue instance that represents the given `int`
     * value.
     *
     * @param value
     * the value to get a JSON representation for
     * @return a JSON value that represents the given value
     */
    fun value(value: Int): JsonValue {
        return JsonNumber(Integer.toString(value, 10))
    }

    /**
     * Returns a JsonValue instance that represents the given `long`
     * value.
     *
     * @param value
     * the value to get a JSON representation for
     * @return a JSON value that represents the given value
     */
    fun value(value: Long): JsonValue {
        return JsonNumber(toString(value, 10))
    }

    /**
     * Returns a JsonValue instance that represents the given `float`
     * value.
     *
     * @param value
     * the value to get a JSON representation for
     * @return a JSON value that represents the given value
     */
    fun value(value: Float): JsonValue {
        if (Float.isInfinite(value) || Float.isNaN(value)) {
            throw IllegalArgumentException(
                    "Infinite and NaN values not permitted in JSON")
        }
        return JsonNumber(cutOffPointZero(toString(value)))
    }

    /**
     * Returns a JsonValue instance that represents the given `double`
     * value.
     *
     * @param value
     * the value to get a JSON representation for
     * @return a JSON value that represents the given value
     */
    fun value(value: Double): JsonValue {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            throw IllegalArgumentException(
                    "Infinite and NaN values not permitted in JSON")
        }
        return JsonNumber(cutOffPointZero(toString(value)))
    }

    /**
     * Returns a JsonValue instance that represents the given string.
     *
     * @param string
     * the string to get a JSON representation for
     * @return a JSON value that represents the given string
     */
    fun value(string: String?): JsonValue {
        return string?.let { JsonString(it) } ?: NULL
    }

    /**
     * Returns a JsonValue instance that represents the given `boolean`
     * value.
     *
     * @param value
     * the value to get a JSON representation for
     * @return a JSON value that represents the given value
     */
    fun value(value: Boolean): JsonValue {
        return if (value) TRUE else FALSE
    }

    /**
     * Creates a new empty JsonArray. This is equivalent to creating a new
     * JsonArray using the
     * constructor.
     *
     * @return a new empty JSON array
     */
    fun array(): JsonArray {
        return JsonArray()
    }

    /**
     * Creates a new JsonArray that contains the JSON representations of the given
     * `int`
     * values.
     *
     * @param values
     * the values to be included in the new JSON array
     * @return a new JSON array that contains the given values
     */
    fun array(vararg values: Int): JsonArray {
        if (values == null) {
            throw NullPointerException("values is null")
        }
        val array = JsonArray()
        for (value in values) {
            array.add(value)
        }
        return array
    }

    /**
     * Creates a new JsonArray that contains the JSON representations of the given
     * `long`
     * values.
     *
     * @param values
     * the values to be included in the new JSON array
     * @return a new JSON array that contains the given values
     */
    fun array(vararg values: Long): JsonArray {
        if (values == null) {
            throw NullPointerException("values is null")
        }
        val array = JsonArray()
        for (value in values) {
            array.add(value)
        }
        return array
    }

    /**
     * Creates a new JsonArray that contains the JSON representations of the given
     * `float`
     * values.
     *
     * @param values
     * the values to be included in the new JSON array
     * @return a new JSON array that contains the given values
     */
    fun array(vararg values: Float): JsonArray {
        if (values == null) {
            throw NullPointerException("values is null")
        }
        val array = JsonArray()
        for (value in values) {
            array.add(value)
        }
        return array
    }

    /**
     * Creates a new JsonArray that contains the JSON representations of the given
     * `double`
     * values.
     *
     * @param values
     * the values to be included in the new JSON array
     * @return a new JSON array that contains the given values
     */
    fun array(vararg values: Double): JsonArray {
        if (values == null) {
            throw NullPointerException("values is null")
        }
        val array = JsonArray()
        for (value in values) {
            array.add(value)
        }
        return array
    }

    /**
     * Creates a new JsonArray that contains the JSON representations of the given
     * `boolean` values.
     *
     * @param values
     * the values to be included in the new JSON array
     * @return a new JSON array that contains the given values
     */
    fun array(vararg values: Boolean): JsonArray {
        if (values == null) {
            throw NullPointerException("values is null")
        }
        val array = JsonArray()
        for (value in values) {
            array.add(value)
        }
        return array
    }

    /**
     * Creates a new JsonArray that contains the JSON representations of the given
     * strings.
     *
     * @param strings
     * the strings to be included in the new JSON array
     * @return a new JSON array that contains the given strings
     */
    fun array(vararg strings: String?): JsonArray {
        if (strings == null) {
            throw NullPointerException("values is null")
        }
        val array = JsonArray()
        for (value in strings) {
            array.add(value)
        }
        return array
    }

    /**
     * Creates a new empty JsonObject. This is equivalent to creating a new
     * JsonObject using the
     * constructor.
     *
     * @return a new empty JSON object
     */
    fun `object`(): JsonObject {
        return JsonObject()
    }

    /**
     * Parses the given input string as JSON. The input must contain a valid JSON
     * value, optionally
     * padded with whitespace.
     *
     * @param string
     * the input string, must be valid JSON
     * @return a value that represents the parsed JSON
     * @throws ParseException
     * if the input is not valid JSON
     */
    fun parse(string: String?): JsonValue? {
        if (string == null) {
            throw NullPointerException("string is null")
        }
        val handler = DefaultHandler()
        JsonParser(handler).parse(string)
        return handler.getValue()
    }

    /**
     * Reads the entire input from the given reader and parses it as JSON. The
     * input must contain a
     * valid JSON value, optionally padded with whitespace.
     *
     *
     * Characters are read in chunks into an input buffer. Hence, wrapping a
     * reader in an additional
     * `BufferedReader` likely won't improve reading performance.
     *
     *
     * @param reader
     * the reader to read the JSON value from
     * @return a value that represents the parsed JSON
     * @throws IOException
     * if an I/O error occurs in the reader
     * @throws ParseException
     * if the input is not valid JSON
     */
    @Throws(IOException::class)
    fun parse(reader: Reader?): JsonValue? {
        if (reader == null) {
            throw NullPointerException("reader is null")
        }
        val handler = DefaultHandler()
        JsonParser(handler).parse(reader)
        return handler.getValue()
    }

    private fun cutOffPointZero(string: String): String {
        return if (string.endsWith(".0")) {
            string.substring(0, string.length() - 2)
        } else string
    }

    internal class DefaultHandler : JsonHandler<JsonArray?, JsonObject?>() {
        protected var value: JsonValue? = null

        @Override
        override fun startArray(): JsonArray {
            return JsonArray()
        }

        @Override
        override fun startObject(): JsonObject {
            return JsonObject()
        }

        @Override
        override fun endNull() {
            value = NULL
        }

        @Override
        override fun endBoolean(bool: Boolean) {
            value = if (bool) TRUE else FALSE
        }

        @Override
        override fun endString(string: String?) {
            value = JsonString(string)
        }

        @Override
        override fun endNumber(string: String?) {
            value = JsonNumber(string)
        }

        @Override
        override fun endArray(array: JsonArray?) {
            value = array
        }

        @Override
        override fun endObject(`object`: JsonObject?) {
            value = `object`
        }

        @Override
        fun endArrayValue(array: JsonArray) {
            array.add(value)
        }

        @Override
        fun endObjectValue(`object`: JsonObject, name: String?) {
            `object`.add(name, value)
        }

        fun getValue(): JsonValue? {
            return value
        }
    }
}