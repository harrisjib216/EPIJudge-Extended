package epi.test_framework.minimal_json

import kotlin.Throws
import kotlin.jvm.Transient
import kotlin.jvm.Synchronized

/**
 * A handler for parser events. Instances of this class can be given to a [ ]. The
 * parser will then call the methods of the given handler while reading the
 * input.
 *
 *
 * The default implementations of these methods do nothing. Subclasses may
 * override only those
 * methods they are interested in. They can use `getLocation()` to
 * access the current
 * character position of the parser at any point. The `start*`
 * methods will be called
 * while the location points to the first character of the parsed element. The
 * `end*`
 * methods will be called while the location points to the character position
 * that directly follows
 * the last character of the parsed element. Example:
 *
 *
 * <pre>
 * ["lorem ipsum"]
 * ^            ^
 * startString  endString
</pre> *
 *
 *
 * Subclasses that build an object representation of the parsed JSON can return
 * arbitrary handler
 * objects for JSON arrays and JSON objects in [.startArray] and [ ][.startObject].
 * These handler objects will then be provided in all subsequent parser events
 * for this particular
 * array or object. They can be used to keep track the elements of a JSON array
 * or object.
 *
 *
 * @param <A>
 * The type of handlers used for JSON arrays
 * @param <O>
 * The type of handlers used for JSON objects
 * @see JsonParser
</O></A> */
abstract class JsonHandler<A, O> {
    var parser: JsonParser? = null

    /**
     * Returns the current parser location.
     *
     * @return the current parser location
     */
    protected val location: epi.test_framework.minimal_json.Location
        protected get() = parser.getLocation()

    /**
     * Indicates the beginning of a `null` literal in the JSON input.
     * This method will be
     * called when reading the first character of the literal.
     */
    fun startNull() {}

    /**
     * Indicates the end of a `null` literal in the JSON input. This
     * method will be called
     * after reading the last character of the literal.
     */
    open fun endNull() {}

    /**
     * Indicates the beginning of a boolean literal (`true` or
     * `false`) in the
     * JSON input. This method will be called when reading the first character of
     * the literal.
     */
    fun startBoolean() {}

    /**
     * Indicates the end of a boolean literal (`true` or
     * `false`) in the JSON
     * input. This method will be called after reading the last character of the
     * literal.
     *
     * @param value
     * the parsed boolean value
     */
    open fun endBoolean(value: Boolean) {}

    /**
     * Indicates the beginning of a string in the JSON input. This method will be
     * called when reading
     * the opening double quote character (`'"'`).
     */
    fun startString() {}

    /**
     * Indicates the end of a string in the JSON input. This method will be called
     * after reading the
     * closing double quote character (`'"'`).
     *
     * @param string
     * the parsed string
     */
    open fun endString(string: String?) {}

    /**
     * Indicates the beginning of a number in the JSON input. This method will be
     * called when reading
     * the first character of the number.
     */
    fun startNumber() {}

    /**
     * Indicates the end of a number in the JSON input. This method will be called
     * after reading the
     * last character of the number.
     *
     * @param string
     * the parsed number string
     */
    open fun endNumber(string: String?) {}

    /**
     * Indicates the beginning of an array in the JSON input. This method will be
     * called when reading
     * the opening square bracket character (`'['`).
     *
     *
     * This method may return an object to handle subsequent parser events for
     * this array. This array
     * handler will then be provided in all calls to [ ][.startArrayValue], [endArrayValue()][.endArrayValue], and
     * [endArray()][.endArray] for this array.
     *
     *
     * @return a handler for this array, or `null` if not needed
     */
    open fun startArray(): A? {
        return null
    }

    /**
     * Indicates the end of an array in the JSON input. This method will be called
     * after reading the
     * closing square bracket character (`']'`).
     *
     * @param array
     * the array handler returned from [.startArray], or
     * `null` if not
     * provided
     */
    fun endArray(array: A) {}

    /**
     * Indicates the beginning of an array element in the JSON input. This method
     * will be called when
     * reading the first character of the element, just before the call to the
     * `start`
     * method for the specific element type ([.startString], [ ][.startNumber], etc.).
     *
     * @param array
     * the array handler returned from [.startArray], or
     * `null` if not
     * provided
     */
    fun startArrayValue(array: A) {}

    /**
     * Indicates the end of an array element in the JSON input. This method will
     * be called after
     * reading the last character of the element value, just after the
     * `end` method for the
     * specific element type (like [endString()][.endString], [ ][.endNumber], etc.).
     *
     * @param array
     * the array handler returned from [.startArray], or
     * `null` if not
     * provided
     */
    fun endArrayValue(array: A) {}

    /**
     * Indicates the beginning of an object in the JSON input. This method will be
     * called when reading
     * the opening curly bracket character (`'{'`).
     *
     *
     * This method may return an object to handle subsequent parser events for
     * this object. This
     * object handler will be provided in all calls to [ ][.startObjectName], [endObjectName()][.endObjectName],
     * [startObjectValue()][.startObjectValue],
     * [endObjectValue()][.endObjectValue], and [ ][.endObject] for this object.
     *
     *
     * @return a handler for this object, or `null` if not needed
     */
    open fun startObject(): O? {
        return null
    }

    /**
     * Indicates the end of an object in the JSON input. This method will be
     * called after reading the
     * closing curly bracket character (`'}'`).
     *
     * @param object
     * the object handler returned from [.startObject], or null
     * if not provided
     */
    fun endObject(`object`: O) {}

    /**
     * Indicates the beginning of the name of an object member in the JSON input.
     * This method will be
     * called when reading the opening quote character ('&quot;') of the member
     * name.
     *
     * @param object
     * the object handler returned from [.startObject], or
     * `null` if not
     * provided
     */
    fun startObjectName(`object`: O) {}

    /**
     * Indicates the end of an object member name in the JSON input. This method
     * will be called after
     * reading the closing quote character (`'"'`) of the member name.
     *
     * @param object
     * the object handler returned from [.startObject], or null
     * if not provided
     * @param name
     * the parsed member name
     */
    fun endObjectName(`object`: O, name: String?) {}

    /**
     * Indicates the beginning of the name of an object member in the JSON input.
     * This method will be
     * called when reading the opening quote character ('&quot;') of the member
     * name.
     *
     * @param object
     * the object handler returned from [.startObject], or
     * `null` if not
     * provided
     * @param name
     * the member name
     */
    fun startObjectValue(`object`: O, name: String?) {}

    /**
     * Indicates the end of an object member value in the JSON input. This method
     * will be called after
     * reading the last character of the member value, just after the
     * `end` method for the
     * specific member type (like [endString()][.endString], [ ][.endNumber], etc.).
     *
     * @param object
     * the object handler returned from [.startObject], or null
     * if not provided
     * @param name
     * the parsed member name
     */
    fun endObjectValue(`object`: O, name: String?) {}
}