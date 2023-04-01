package epi.test_framework.minimal_json

import kotlin.Throws
import kotlin.jvm.Transient
import kotlin.jvm.Synchronized

/**
 * An unchecked exception to indicate that an input does not qualify as valid
 * JSON.
 */
@SuppressWarnings("serial") // use default serial UID
class ParseException internal constructor(message: String, location: Location) : RuntimeException("$message at $location") {
    private val location: Location

    init {
        this.location = location
    }

    /**
     * Returns the location at which the error occurred.
     *
     * @return the error location
     */
    fun getLocation(): Location {
        return location
    }

    /**
     * Returns the absolute character index at which the error occurred. The
     * offset of the first
     * character of a document is 0.
     *
     * @return the character offset at which the error occurred, will be &gt;= 0
     */
    @get:Deprecated("Use {@link #getLocation()} instead")
    @get:Deprecated
    val offset: Int
        get() = location.offset

    /**
     * Returns the line number in which the error occurred. The number of the
     * first line is 1.
     *
     * @return the line in which the error occurred, will be &gt;= 1
     */
    @get:Deprecated("Use {@link #getLocation()} instead")
    @get:Deprecated
    val line: Int
        get() = location.line

    /**
     * Returns the column number at which the error occurred, i.e. the number of
     * the character in its
     * line. The number of the first character of a line is 1.
     *
     * @return the column in which the error occurred, will be &gt;= 1
     */
    @get:Deprecated("Use {@link #getLocation()} instead")
    @get:Deprecated
    val column: Int
        get() = location.column
}