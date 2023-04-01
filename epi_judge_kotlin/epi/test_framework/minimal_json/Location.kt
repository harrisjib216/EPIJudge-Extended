package epi.test_framework.minimal_json

import kotlin.Throws
import kotlin.jvm.Transient
import kotlin.jvm.Synchronized

/**
 * An immutable object that represents a location in the parsed text.
 */
class Location(
        /**
         * The absolute character index, starting at 0.
         */
        val offset: Int,
        /**
         * The line number, starting at 1.
         */
        val line: Int,
        /**
         * The column number, starting at 1.
         */
        val column: Int) {
    @Override
    override fun toString(): String {
        return "$line:$column"
    }

    @Override
    override fun hashCode(): Int {
        return offset
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
        val other = obj as Location
        return offset == other.offset && column == other.column && line == other.line
    }
}