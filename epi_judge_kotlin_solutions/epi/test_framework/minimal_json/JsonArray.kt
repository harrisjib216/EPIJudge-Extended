package epi.test_framework.minimal_json

import java.io.IOException

/**
 * Represents a JSON array, an ordered collection of JSON values.
 *
 *
 * Elements can be added using the `add(...)` methods which accept
 * instances of
 * [JsonValue], strings, primitive numbers, and boolean values. To replace
 * an element of an
 * array, use the `set(int, ...)` methods.
 *
 *
 *
 * Elements can be accessed by their index using [.get]. This class
 * also supports
 * iterating over the elements in document order using an [.iterator] or
 * an enhanced for
 * loop:
 *
 * <pre>
 * for (JsonValue value : jsonArray) {
 * ...
 * }
</pre> *
 *
 *
 * An equivalent [List] can be obtained from the method [.values].
 *
 *
 *
 * Note that this class is **not thread-safe**. If multiple threads
 * access a
 * `JsonArray` instance concurrently, while at least one of these
 * threads modifies the
 * contents of this array, access to the instance must be synchronized
 * externally. Failure to do so
 * may lead to an inconsistent state.
 *
 *
 *
 * This class is **not supposed to be extended** by clients.
 *
 */
@SuppressWarnings("serial") // use default serial UID
class JsonArray : JsonValue, Iterable<JsonValue?> {
    private val values: List<JsonValue>

    /**
     * Creates a new empty JsonArray.
     */
    constructor() {
        values = ArrayList<JsonValue>()
    }

    /**
     * Creates a new JsonArray with the contents of the specified JSON array.
     *
     * @param array
     * the JsonArray to get the initial contents from, must not be
     * `null`
     */
    constructor(array: JsonArray?) : this(array, false) {}
    private constructor(array: JsonArray?, unmodifiable: Boolean) {
        if (array == null) {
            throw NullPointerException("array is null")
        }
        if (unmodifiable) {
            values = Collections.unmodifiableList(array.values)
        } else {
            values = ArrayList<JsonValue>(array.values)
        }
    }

    /**
     * Appends the JSON representation of the specified `int` value to
     * the end of this
     * array.
     *
     * @param value
     * the value to add to the array
     * @return the array itself, to enable method chaining
     */
    fun add(value: Int): JsonArray {
        values.add(Json.value(value))
        return this
    }

    /**
     * Appends the JSON representation of the specified `long` value to
     * the end of this
     * array.
     *
     * @param value
     * the value to add to the array
     * @return the array itself, to enable method chaining
     */
    fun add(value: Long): JsonArray {
        values.add(Json.value(value))
        return this
    }

    /**
     * Appends the JSON representation of the specified `float` value
     * to the end of this
     * array.
     *
     * @param value
     * the value to add to the array
     * @return the array itself, to enable method chaining
     */
    fun add(value: Float): JsonArray {
        values.add(Json.value(value))
        return this
    }

    /**
     * Appends the JSON representation of the specified `double` value
     * to the end of this
     * array.
     *
     * @param value
     * the value to add to the array
     * @return the array itself, to enable method chaining
     */
    fun add(value: Double): JsonArray {
        values.add(Json.value(value))
        return this
    }

    /**
     * Appends the JSON representation of the specified `boolean` value
     * to the end of this
     * array.
     *
     * @param value
     * the value to add to the array
     * @return the array itself, to enable method chaining
     */
    fun add(value: Boolean): JsonArray {
        values.add(Json.value(value))
        return this
    }

    /**
     * Appends the JSON representation of the specified string to the end of this
     * array.
     *
     * @param value
     * the string to add to the array
     * @return the array itself, to enable method chaining
     */
    fun add(value: String?): JsonArray {
        values.add(Json.value(value))
        return this
    }

    /**
     * Appends the specified JSON value to the end of this array.
     *
     * @param value
     * the JsonValue to add to the array, must not be `null`
     * @return the array itself, to enable method chaining
     */
    fun add(value: JsonValue?): JsonArray {
        if (value == null) {
            throw NullPointerException("value is null")
        }
        values.add(value)
        return this
    }

    /**
     * Replaces the element at the specified position in this array with the JSON
     * representation of
     * the specified `int` value.
     *
     * @param index
     * the index of the array element to replace
     * @param value
     * the value to be stored at the specified array position
     * @return the array itself, to enable method chaining
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    operator fun set(index: Int, value: Int): JsonArray {
        values.set(index, Json.value(value))
        return this
    }

    /**
     * Replaces the element at the specified position in this array with the JSON
     * representation of
     * the specified `long` value.
     *
     * @param index
     * the index of the array element to replace
     * @param value
     * the value to be stored at the specified array position
     * @return the array itself, to enable method chaining
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    operator fun set(index: Int, value: Long): JsonArray {
        values.set(index, Json.value(value))
        return this
    }

    /**
     * Replaces the element at the specified position in this array with the JSON
     * representation of
     * the specified `float` value.
     *
     * @param index
     * the index of the array element to replace
     * @param value
     * the value to be stored at the specified array position
     * @return the array itself, to enable method chaining
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    operator fun set(index: Int, value: Float): JsonArray {
        values.set(index, Json.value(value))
        return this
    }

    /**
     * Replaces the element at the specified position in this array with the JSON
     * representation of
     * the specified `double` value.
     *
     * @param index
     * the index of the array element to replace
     * @param value
     * the value to be stored at the specified array position
     * @return the array itself, to enable method chaining
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    operator fun set(index: Int, value: Double): JsonArray {
        values.set(index, Json.value(value))
        return this
    }

    /**
     * Replaces the element at the specified position in this array with the JSON
     * representation of
     * the specified `boolean` value.
     *
     * @param index
     * the index of the array element to replace
     * @param value
     * the value to be stored at the specified array position
     * @return the array itself, to enable method chaining
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    operator fun set(index: Int, value: Boolean): JsonArray {
        values.set(index, Json.value(value))
        return this
    }

    /**
     * Replaces the element at the specified position in this array with the JSON
     * representation of
     * the specified string.
     *
     * @param index
     * the index of the array element to replace
     * @param value
     * the string to be stored at the specified array position
     * @return the array itself, to enable method chaining
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    operator fun set(index: Int, value: String?): JsonArray {
        values.set(index, Json.value(value))
        return this
    }

    /**
     * Replaces the element at the specified position in this array with the
     * specified JSON value.
     *
     * @param index
     * the index of the array element to replace
     * @param value
     * the value to be stored at the specified array position, must not
     * be `null`
     * @return the array itself, to enable method chaining
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    operator fun set(index: Int, value: JsonValue?): JsonArray {
        if (value == null) {
            throw NullPointerException("value is null")
        }
        values.set(index, value)
        return this
    }

    /**
     * Removes the element at the specified index from this array.
     *
     * @param index
     * the index of the element to remove
     * @return the array itself, to enable method chaining
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    fun remove(index: Int): JsonArray {
        values.remove(index)
        return this
    }

    /**
     * Returns the number of elements in this array.
     *
     * @return the number of elements in this array
     */
    fun size(): Int {
        return values.size()
    }

    /**
     * Returns `true` if this array contains no elements.
     *
     * @return `true` if this array contains no elements
     */
    val isEmpty: Boolean
        get() = values.isEmpty()

    /**
     * Returns the value of the element at the specified position in this array.
     *
     * @param index
     * the index of the array element to return
     * @return the value of the element at the specified position
     * @throws IndexOutOfBoundsException
     * if the index is out of range, i.e. `index < 0` or
     * `index >= size`
     */
    operator fun get(index: Int): JsonValue {
        return values[index]
    }

    /**
     * Returns a list of the values in this array in document order. The returned
     * list is backed by
     * this array and will reflect subsequent changes. It cannot be used to modify
     * this array.
     * Attempts to modify the returned list will result in an exception.
     *
     * @return a list of the values in this array
     */
    fun values(): List<JsonValue> {
        return Collections.unmodifiableList(values)
    }

    /**
     * Returns an iterator over the values of this array in document order. The
     * returned iterator
     * cannot be used to modify this array.
     *
     * @return an iterator over the values of this array
     */
    override fun iterator(): Iterator<JsonValue> {
        val iterator: Iterator<JsonValue> = values.iterator()
        return object : Iterator<JsonValue?>() {
            override fun hasNext(): Boolean {
                return iterator.hasNext()
            }

            override fun next(): JsonValue {
                return iterator.next()
            }

            fun remove() {
                throw UnsupportedOperationException()
            }
        }
    }

    @Override
    @Throws(IOException::class)
    fun write(writer: JsonWriter) {
        writer.writeArrayOpen()
        val iterator: Iterator<JsonValue> = iterator()
        if (iterator.hasNext()) {
            iterator.next().write(writer)
            while (iterator.hasNext()) {
                writer.writeArraySeparator()
                iterator.next().write(writer)
            }
        }
        writer.writeArrayClose()
    }

    @get:Override
    override val isArray: Boolean
        get() = true

    @Override
    override fun asArray(): JsonArray {
        return this
    }

    @Override
    override fun hashCode(): Int {
        return values.hashCode()
    }

    /**
     * Indicates whether a given object is "equal to" this JsonArray. An object is
     * considered equal
     * if it is also a `JsonArray` and both arrays contain the same
     * list of values.
     *
     *
     * If two JsonArrays are equal, they will also produce the same JSON output.
     *
     *
     * @param object
     * the object to be compared with this JsonArray
     * @return <tt>true</tt> if the specified object is equal to this JsonArray,
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
        val other = `object` as JsonArray
        return values.equals(other.values)
    }

    companion object {
        /**
         * Reads a JSON array from the given reader.
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
         * the reader to read the JSON array from
         * @return the JSON array that has been read
         * @throws IOException
         * if an I/O error occurs in the reader
         * @throws ParseException
         * if the input is not valid JSON
         * @throws UnsupportedOperationException
         * if the input does not contain a JSON array
         */
        @Deprecated
        @Deprecated("""Use {@link Json#parse(Reader)}{@link JsonValue#asArray()
   * .asArray()} instead""")
        @Throws(IOException::class)
        fun readFrom(reader: Reader?): JsonArray {
            return JsonValue.readFrom(reader).asArray()
        }

        /**
         * Reads a JSON array from the given string.
         *
         * @param string
         * the string that contains the JSON array
         * @return the JSON array that has been read
         * @throws ParseException
         * if the input is not valid JSON
         * @throws UnsupportedOperationException
         * if the input does not contain a JSON array
         */
        @Deprecated
        @Deprecated("""Use {@link Json#parse(String)}{@link JsonValue#asArray()
   * .asArray()} instead""")
        fun readFrom(string: String?): JsonArray {
            return JsonValue.readFrom(string).asArray()
        }

        /**
         * Returns an unmodifiable wrapper for the specified JsonArray. This method
         * allows to provide
         * read-only access to a JsonArray.
         *
         *
         * The returned JsonArray is backed by the given array and reflects subsequent
         * changes. Attempts
         * to modify the returned JsonArray result in an
         * `UnsupportedOperationException`.
         *
         *
         * @param array
         * the JsonArray for which an unmodifiable JsonArray is to be
         * returned
         * @return an unmodifiable view of the specified JsonArray
         */
        fun unmodifiableArray(array: JsonArray?): JsonArray {
            return JsonArray(array, true)
        }
    }
}