package epi.test_framework.minimal_json

import java.io.IOException

/**
 * Represents a JSON object, a set of name/value pairs, where the names are
 * strings and the values
 * are JSON values.
 *
 *
 * Members can be added using the `add(String, ...)` methods which
 * accept instances of
 * [JsonValue], strings, primitive numbers, and boolean values. To modify
 * certain values of an
 * object, use the `set(String, ...)` methods. Please note that the
 * `add`
 * methods are faster than `set` as they do not search for existing
 * members. On the other
 * hand, the `add` methods do not prevent adding multiple members
 * with the same name.
 * Duplicate names are discouraged but not prohibited by JSON.
 *
 *
 *
 * Members can be accessed by their name using [.get]. A list of
 * all names can be
 * obtained from the method [.names]. This class also supports iterating
 * over the members in
 * document order using an [.iterator] or an enhanced for loop:
 *
 * <pre>
 * for (Member member : jsonObject) {
 * String name = member.getName();
 * JsonValue value = member.getValue();
 * ...
 * }
</pre> *
 *
 *
 * Even though JSON objects are unordered by definition, instances of this class
 * preserve the order
 * of members to allow processing in document order and to guarantee a
 * predictable output.
 *
 *
 *
 * Note that this class is **not thread-safe**. If multiple threads
 * access a
 * `JsonObject` instance concurrently, while at least one of these
 * threads modifies the
 * contents of this object, access to the instance must be synchronized
 * externally. Failure to do so
 * may lead to an inconsistent state.
 *
 *
 *
 * This class is **not supposed to be extended** by clients.
 *
 */
@SuppressWarnings("serial") // use default serial UID
class JsonObject : JsonValue, Iterable<Member?> {
    private val names: List<String>
    private val values: List<JsonValue>

    @Transient
    private var table: HashIndexTable

    /**
     * Creates a new empty JsonObject.
     */
    constructor() {
        names = ArrayList<String>()
        values = ArrayList<JsonValue>()
        table = HashIndexTable()
    }

    /**
     * Creates a new JsonObject, initialized with the contents of the specified
     * JSON object.
     *
     * @param object
     * the JSON object to get the initial contents from, must not be
     * `null`
     */
    constructor(`object`: JsonObject?) : this(`object`, false) {}
    private constructor(`object`: JsonObject?, unmodifiable: Boolean) {
        if (`object` == null) {
            throw NullPointerException("object is null")
        }
        if (unmodifiable) {
            names = Collections.unmodifiableList(`object`.names)
            values = Collections.unmodifiableList(`object`.values)
        } else {
            names = ArrayList<String>(`object`.names)
            values = ArrayList<JsonValue>(`object`.values)
        }
        table = HashIndexTable()
        updateHashIndex()
    }

    /**
     * Appends a new member to the end of this object, with the specified name and
     * the JSON
     * representation of the specified `int` value.
     *
     *
     * This method **does not prevent duplicate names**. Calling this
     * method with a name
     * that already exists in the object will append another member with the same
     * name. In order to
     * replace existing members, use the method `set(name, value)`
     * instead. However,
     * ** *add* is much faster than *set*** (because it
     * does not need to
     * search for existing members). Therefore *add* should be preferred
     * when constructing new
     * objects.
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    fun add(name: String?, value: Int): JsonObject {
        add(name, Json.value(value))
        return this
    }

    /**
     * Appends a new member to the end of this object, with the specified name and
     * the JSON
     * representation of the specified `long` value.
     *
     *
     * This method **does not prevent duplicate names**. Calling this
     * method with a name
     * that already exists in the object will append another member with the same
     * name. In order to
     * replace existing members, use the method `set(name, value)`
     * instead. However,
     * ** *add* is much faster than *set*** (because it
     * does not need to
     * search for existing members). Therefore *add* should be preferred
     * when constructing new
     * objects.
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    fun add(name: String?, value: Long): JsonObject {
        add(name, Json.value(value))
        return this
    }

    /**
     * Appends a new member to the end of this object, with the specified name and
     * the JSON
     * representation of the specified `float` value.
     *
     *
     * This method **does not prevent duplicate names**. Calling this
     * method with a name
     * that already exists in the object will append another member with the same
     * name. In order to
     * replace existing members, use the method `set(name, value)`
     * instead. However,
     * ** *add* is much faster than *set*** (because it
     * does not need to
     * search for existing members). Therefore *add* should be preferred
     * when constructing new
     * objects.
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    fun add(name: String?, value: Float): JsonObject {
        add(name, Json.value(value))
        return this
    }

    /**
     * Appends a new member to the end of this object, with the specified name and
     * the JSON
     * representation of the specified `double` value.
     *
     *
     * This method **does not prevent duplicate names**. Calling this
     * method with a name
     * that already exists in the object will append another member with the same
     * name. In order to
     * replace existing members, use the method `set(name, value)`
     * instead. However,
     * ** *add* is much faster than *set*** (because it
     * does not need to
     * search for existing members). Therefore *add* should be preferred
     * when constructing new
     * objects.
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    fun add(name: String?, value: Double): JsonObject {
        add(name, Json.value(value))
        return this
    }

    /**
     * Appends a new member to the end of this object, with the specified name and
     * the JSON
     * representation of the specified `boolean` value.
     *
     *
     * This method **does not prevent duplicate names**. Calling this
     * method with a name
     * that already exists in the object will append another member with the same
     * name. In order to
     * replace existing members, use the method `set(name, value)`
     * instead. However,
     * ** *add* is much faster than *set*** (because it
     * does not need to
     * search for existing members). Therefore *add* should be preferred
     * when constructing new
     * objects.
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    fun add(name: String?, value: Boolean): JsonObject {
        add(name, Json.value(value))
        return this
    }

    /**
     * Appends a new member to the end of this object, with the specified name and
     * the JSON
     * representation of the specified string.
     *
     *
     * This method **does not prevent duplicate names**. Calling this
     * method with a name
     * that already exists in the object will append another member with the same
     * name. In order to
     * replace existing members, use the method `set(name, value)`
     * instead. However,
     * ** *add* is much faster than *set*** (because it
     * does not need to
     * search for existing members). Therefore *add* should be preferred
     * when constructing new
     * objects.
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    fun add(name: String?, value: String?): JsonObject {
        add(name, Json.value(value))
        return this
    }

    /**
     * Appends a new member to the end of this object, with the specified name and
     * the specified JSON
     * value.
     *
     *
     * This method **does not prevent duplicate names**. Calling this
     * method with a name
     * that already exists in the object will append another member with the same
     * name. In order to
     * replace existing members, use the method `set(name, value)`
     * instead. However,
     * ** *add* is much faster than *set*** (because it
     * does not need to
     * search for existing members). Therefore *add* should be preferred
     * when constructing new
     * objects.
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add, must not be `null`
     * @return the object itself, to enable method chaining
     */
    fun add(name: String?, value: JsonValue?): JsonObject {
        if (name == null) {
            throw NullPointerException("name is null")
        }
        if (value == null) {
            throw NullPointerException("value is null")
        }
        table.add(name, names.size())
        names.add(name)
        values.add(value)
        return this
    }

    /**
     * Sets the value of the member with the specified name to the JSON
     * representation of the
     * specified `int` value. If this object does not contain a member
     * with this name, a
     * new member is added at the end of the object. If this object contains
     * multiple members with
     * this name, only the last one is changed.
     *
     *
     * This method should **only be used to modify existing
     * objects**. To fill a new
     * object with members, the method `add(name, value)` should be
     * preferred which is much
     * faster (as it does not need to search for existing members).
     *
     *
     * @param name
     * the name of the member to replace
     * @param value
     * the value to set to the member
     * @return the object itself, to enable method chaining
     */
    operator fun set(name: String?, value: Int): JsonObject {
        set(name, Json.value(value))
        return this
    }

    /**
     * Sets the value of the member with the specified name to the JSON
     * representation of the
     * specified `long` value. If this object does not contain a member
     * with this name, a
     * new member is added at the end of the object. If this object contains
     * multiple members with
     * this name, only the last one is changed.
     *
     *
     * This method should **only be used to modify existing
     * objects**. To fill a new
     * object with members, the method `add(name, value)` should be
     * preferred which is much
     * faster (as it does not need to search for existing members).
     *
     *
     * @param name
     * the name of the member to replace
     * @param value
     * the value to set to the member
     * @return the object itself, to enable method chaining
     */
    operator fun set(name: String?, value: Long): JsonObject {
        set(name, Json.value(value))
        return this
    }

    /**
     * Sets the value of the member with the specified name to the JSON
     * representation of the
     * specified `float` value. If this object does not contain a
     * member with this name, a
     * new member is added at the end of the object. If this object contains
     * multiple members with
     * this name, only the last one is changed.
     *
     *
     * This method should **only be used to modify existing
     * objects**. To fill a new
     * object with members, the method `add(name, value)` should be
     * preferred which is much
     * faster (as it does not need to search for existing members).
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    operator fun set(name: String?, value: Float): JsonObject {
        set(name, Json.value(value))
        return this
    }

    /**
     * Sets the value of the member with the specified name to the JSON
     * representation of the
     * specified `double` value. If this object does not contain a
     * member with this name, a
     * new member is added at the end of the object. If this object contains
     * multiple members with
     * this name, only the last one is changed.
     *
     *
     * This method should **only be used to modify existing
     * objects**. To fill a new
     * object with members, the method `add(name, value)` should be
     * preferred which is much
     * faster (as it does not need to search for existing members).
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    operator fun set(name: String?, value: Double): JsonObject {
        set(name, Json.value(value))
        return this
    }

    /**
     * Sets the value of the member with the specified name to the JSON
     * representation of the
     * specified `boolean` value. If this object does not contain a
     * member with this name,
     * a new member is added at the end of the object. If this object contains
     * multiple members with
     * this name, only the last one is changed.
     *
     *
     * This method should **only be used to modify existing
     * objects**. To fill a new
     * object with members, the method `add(name, value)` should be
     * preferred which is much
     * faster (as it does not need to search for existing members).
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    operator fun set(name: String?, value: Boolean): JsonObject {
        set(name, Json.value(value))
        return this
    }

    /**
     * Sets the value of the member with the specified name to the JSON
     * representation of the
     * specified string. If this object does not contain a member with this name,
     * a new member is
     * added at the end of the object. If this object contains multiple members
     * with this name, only
     * the last one is changed.
     *
     *
     * This method should **only be used to modify existing
     * objects**. To fill a new
     * object with members, the method `add(name, value)` should be
     * preferred which is much
     * faster (as it does not need to search for existing members).
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add
     * @return the object itself, to enable method chaining
     */
    operator fun set(name: String?, value: String?): JsonObject {
        set(name, Json.value(value))
        return this
    }

    /**
     * Sets the value of the member with the specified name to the specified JSON
     * value. If this
     * object does not contain a member with this name, a new member is added at
     * the end of the
     * object. If this object contains multiple members with this name, only the
     * last one is changed.
     *
     *
     * This method should **only be used to modify existing
     * objects**. To fill a new
     * object with members, the method `add(name, value)` should be
     * preferred which is much
     * faster (as it does not need to search for existing members).
     *
     *
     * @param name
     * the name of the member to add
     * @param value
     * the value of the member to add, must not be `null`
     * @return the object itself, to enable method chaining
     */
    operator fun set(name: String?, value: JsonValue?): JsonObject {
        if (name == null) {
            throw NullPointerException("name is null")
        }
        if (value == null) {
            throw NullPointerException("value is null")
        }
        val index = indexOf(name)
        if (index != -1) {
            values.set(index, value)
        } else {
            table.add(name, names.size())
            names.add(name)
            values.add(value)
        }
        return this
    }

    /**
     * Removes a member with the specified name from this object. If this object
     * contains multiple
     * members with the given name, only the last one is removed. If this object
     * does not contain a
     * member with the specified name, the object is not modified.
     *
     * @param name
     * the name of the member to remove
     * @return the object itself, to enable method chaining
     */
    fun remove(name: String?): JsonObject {
        if (name == null) {
            throw NullPointerException("name is null")
        }
        val index = indexOf(name)
        if (index != -1) {
            table.remove(index)
            names.remove(index)
            values.remove(index)
        }
        return this
    }

    /**
     * Copies all members of the specified object into this object. When the
     * specified object contains
     * members with names that also exist in this object, the existing values in
     * this object will be
     * replaced by the corresponding values in the specified object.
     *
     * @param object
     * the object to merge
     * @return the object itself, to enable method chaining
     */
    fun merge(`object`: JsonObject?): JsonObject {
        if (`object` == null) {
            throw NullPointerException("object is null")
        }
        for (member in `object`) {
            this[member.getName()] = member.getValue()
        }
        return this
    }

    /**
     * Returns the value of the member with the specified name in this object. If
     * this object contains
     * multiple members with the given name, this method will return the last one.
     *
     * @param name
     * the name of the member whose value is to be returned
     * @return the value of the last member with the specified name, or
     * `null` if this
     * object does not contain a member with that name
     */
    operator fun get(name: String?): JsonValue? {
        if (name == null) {
            throw NullPointerException("name is null")
        }
        val index = indexOf(name)
        return if (index != -1) values[index] else null
    }

    /**
     * Returns the `int` value of the member with the specified name in
     * this object. If
     * this object does not contain a member with this name, the given default
     * value is returned. If
     * this object contains multiple members with the given name, the last one
     * will be picked. If this
     * member's value does not represent a JSON number or if it cannot be
     * interpreted as Java
     * `int`, an exception is thrown.
     *
     * @param name
     * the name of the member whose value is to be returned
     * @param defaultValue
     * the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given
     * default value if
     * this object does not contain a member with that name
     */
    fun getInt(name: String?, defaultValue: Int): Int {
        val value: JsonValue? = get(name)
        return if (value != null) value.asInt() else defaultValue
    }

    /**
     * Returns the `long` value of the member with the specified name
     * in this object. If
     * this object does not contain a member with this name, the given default
     * value is returned. If
     * this object contains multiple members with the given name, the last one
     * will be picked. If this
     * member's value does not represent a JSON number or if it cannot be
     * interpreted as Java
     * `long`, an exception is thrown.
     *
     * @param name
     * the name of the member whose value is to be returned
     * @param defaultValue
     * the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given
     * default value if
     * this object does not contain a member with that name
     */
    fun getLong(name: String?, defaultValue: Long): Long {
        val value: JsonValue? = get(name)
        return if (value != null) value.asLong() else defaultValue
    }

    /**
     * Returns the `float` value of the member with the specified name
     * in this object. If
     * this object does not contain a member with this name, the given default
     * value is returned. If
     * this object contains multiple members with the given name, the last one
     * will be picked. If this
     * member's value does not represent a JSON number or if it cannot be
     * interpreted as Java
     * `float`, an exception is thrown.
     *
     * @param name
     * the name of the member whose value is to be returned
     * @param defaultValue
     * the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given
     * default value if
     * this object does not contain a member with that name
     */
    fun getFloat(name: String?, defaultValue: Float): Float {
        val value: JsonValue? = get(name)
        return if (value != null) value.asFloat() else defaultValue
    }

    /**
     * Returns the `double` value of the member with the specified name
     * in this object. If
     * this object does not contain a member with this name, the given default
     * value is returned. If
     * this object contains multiple members with the given name, the last one
     * will be picked. If this
     * member's value does not represent a JSON number or if it cannot be
     * interpreted as Java
     * `double`, an exception is thrown.
     *
     * @param name
     * the name of the member whose value is to be returned
     * @param defaultValue
     * the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given
     * default value if
     * this object does not contain a member with that name
     */
    fun getDouble(name: String?, defaultValue: Double): Double {
        val value: JsonValue? = get(name)
        return if (value != null) value.asDouble() else defaultValue
    }

    /**
     * Returns the `boolean` value of the member with the specified
     * name in this object. If
     * this object does not contain a member with this name, the given default
     * value is returned. If
     * this object contains multiple members with the given name, the last one
     * will be picked. If this
     * member's value does not represent a JSON `true` or
     * `false` value, an
     * exception is thrown.
     *
     * @param name
     * the name of the member whose value is to be returned
     * @param defaultValue
     * the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given
     * default value if
     * this object does not contain a member with that name
     */
    fun getBoolean(name: String?, defaultValue: Boolean): Boolean {
        val value: JsonValue? = get(name)
        return if (value != null) value.asBoolean() else defaultValue
    }

    /**
     * Returns the `String` value of the member with the specified name
     * in this object. If
     * this object does not contain a member with this name, the given default
     * value is returned. If
     * this object contains multiple members with the given name, the last one is
     * picked. If this
     * member's value does not represent a JSON string, an exception is thrown.
     *
     * @param name
     * the name of the member whose value is to be returned
     * @param defaultValue
     * the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given
     * default value if
     * this object does not contain a member with that name
     */
    fun getString(name: String?, defaultValue: String?): String {
        val value: JsonValue? = get(name)
        return if (value != null) value.asString() else defaultValue!!
    }

    /**
     * Returns the number of members (name/value pairs) in this object.
     *
     * @return the number of members in this object
     */
    fun size(): Int {
        return names.size()
    }

    /**
     * Returns `true` if this object contains no members.
     *
     * @return `true` if this object contains no members
     */
    val isEmpty: Boolean
        get() = names.isEmpty()

    /**
     * Returns a list of the names in this object in document order. The returned
     * list is backed by
     * this object and will reflect subsequent changes. It cannot be used to
     * modify this object.
     * Attempts to modify the returned list will result in an exception.
     *
     * @return a list of the names in this object
     */
    fun names(): List<String> {
        return Collections.unmodifiableList(names)
    }

    /**
     * Returns an iterator over the members of this object in document order. The
     * returned iterator
     * cannot be used to modify this object.
     *
     * @return an iterator over the members of this object
     */
    override fun iterator(): Iterator<Member> {
        val namesIterator = names.iterator()
        val valuesIterator: Iterator<JsonValue> = values.iterator()
        return object : Iterator<Member?>() {
            override fun hasNext(): Boolean {
                return namesIterator.hasNext()
            }

            override fun next(): Member {
                val name = namesIterator.next()
                val value: JsonValue = valuesIterator.next()
                return Member(name, value)
            }

            fun remove() {
                throw UnsupportedOperationException()
            }
        }
    }

    @Override
    @Throws(IOException::class)
    fun write(writer: JsonWriter) {
        writer.writeObjectOpen()
        val namesIterator = names.iterator()
        val valuesIterator: Iterator<JsonValue> = values.iterator()
        if (namesIterator.hasNext()) {
            writer.writeMemberName(namesIterator.next())
            writer.writeMemberSeparator()
            valuesIterator.next().write(writer)
            while (namesIterator.hasNext()) {
                writer.writeObjectSeparator()
                writer.writeMemberName(namesIterator.next())
                writer.writeMemberSeparator()
                valuesIterator.next().write(writer)
            }
        }
        writer.writeObjectClose()
    }

    @get:Override
    override val isObject: Boolean
        get() = true

    @Override
    override fun asObject(): JsonObject {
        return this
    }

    @Override
    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + names.hashCode()
        result = 31 * result + values.hashCode()
        return result
    }

    @Override
    override fun equals(obj: Object?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (getClass() !== obj.getClass()) {
            return false
        }
        val other = obj as JsonObject
        return names.equals(other.names) && values.equals(other.values)
    }

    fun indexOf(name: String): Int {
        val index = table[name]
        return if (index != -1 && name.equals(names[index])) {
            index
        } else names.lastIndexOf(name)
    }

    @Synchronized
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inputStream: ObjectInputStream) {
        inputStream.defaultReadObject()
        table = HashIndexTable()
        updateHashIndex()
    }

    private fun updateHashIndex() {
        val size: Int = names.size()
        for (i in 0 until size) {
            table.add(names[i], i)
        }
    }

    internal class HashIndexTable {
        private val hashTable = ByteArray(32) // must be a power of two

        constructor() {}
        constructor(original: HashIndexTable) {
            System.arraycopy(original.hashTable, 0, hashTable, 0, hashTable.size)
        }

        fun add(name: String, index: Int) {
            val slot = hashSlotFor(name)
            if (index < 0xff) {
                // increment by 1, 0 stands for empty
                hashTable[slot] = (index + 1).toByte()
            } else {
                hashTable[slot] = 0
            }
        }

        fun remove(index: Int) {
            for (i in hashTable.indices) {
                if (hashTable[i].toInt() == index + 1) {
                    hashTable[i] = 0
                } else if (hashTable[i] > index + 1) {
                    hashTable[i]--
                }
            }
        }

        operator fun get(name: Object): Int {
            val slot = hashSlotFor(name)
            // subtract 1, 0 stands for empty
            return (hashTable[slot].toInt() and 0xff) - 1
        }

        private fun hashSlotFor(element: Object): Int {
            return element.hashCode() and hashTable.size - 1
        }
    }

    companion object {
        /**
         * Reads a JSON object from the given reader.
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
         * the reader to read the JSON object from
         * @return the JSON object that has been read
         * @throws IOException
         * if an I/O error occurs in the reader
         * @throws ParseException
         * if the input is not valid JSON
         * @throws UnsupportedOperationException
         * if the input does not contain a JSON object
         */
        @Deprecated
        @Deprecated("""Use {@link Json#parse(Reader)}{@link JsonValue#asObject()
   * .asObject()} instead""")
        @Throws(IOException::class)
        fun readFrom(reader: Reader?): JsonObject {
            return JsonValue.readFrom(reader).asObject()
        }

        /**
         * Reads a JSON object from the given string.
         *
         * @param string
         * the string that contains the JSON object
         * @return the JSON object that has been read
         * @throws ParseException
         * if the input is not valid JSON
         * @throws UnsupportedOperationException
         * if the input does not contain a JSON object
         */
        @Deprecated
        @Deprecated("""Use {@link Json#parse(String)}{@link JsonValue#asObject()
   * .asObject()} instead""")
        fun readFrom(string: String?): JsonObject {
            return JsonValue.readFrom(string).asObject()
        }

        /**
         * Returns an unmodifiable JsonObject for the specified one. This method
         * allows to provide
         * read-only access to a JsonObject.
         *
         *
         * The returned JsonObject is backed by the given object and reflect changes
         * that happen to it.
         * Attempts to modify the returned JsonObject result in an
         * `UnsupportedOperationException`.
         *
         *
         * @param object
         * the JsonObject for which an unmodifiable JsonObject is to be
         * returned
         * @return an unmodifiable view of the specified JsonObject
         */
        fun unmodifiableObject(`object`: JsonObject?): JsonObject {
            return JsonObject(`object`, true)
        }
    }
}