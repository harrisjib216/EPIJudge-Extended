package epi.test_framework.minimal_json

import java.io.IOException

/*******************************************************************************
 * Copyright (c) 2013, 2015 EclipseSource.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
open class JsonWriter internal constructor(writer: Writer) {
    protected val writer: Writer

    init {
        this.writer = writer
    }

    @Throws(IOException::class)
    fun writeLiteral(value: String?) {
        writer.write(value)
    }

    @Throws(IOException::class)
    fun writeNumber(string: String?) {
        writer.write(string)
    }

    @Throws(IOException::class)
    fun writeString(string: String) {
        writer.write('"')
        writeJsonString(string)
        writer.write('"')
    }

    @Throws(IOException::class)
    open fun writeArrayOpen() {
        writer.write('[')
    }

    @Throws(IOException::class)
    open fun writeArrayClose() {
        writer.write(']')
    }

    @Throws(IOException::class)
    open fun writeArraySeparator() {
        writer.write(',')
    }

    @Throws(IOException::class)
    open fun writeObjectOpen() {
        writer.write('{')
    }

    @Throws(IOException::class)
    open fun writeObjectClose() {
        writer.write('}')
    }

    @Throws(IOException::class)
    fun writeMemberName(name: String) {
        writer.write('"')
        writeJsonString(name)
        writer.write('"')
    }

    @Throws(IOException::class)
    open fun writeMemberSeparator() {
        writer.write(':')
    }

    @Throws(IOException::class)
    open fun writeObjectSeparator() {
        writer.write(',')
    }

    @Throws(IOException::class)
    protected fun writeJsonString(string: String) {
        val length: Int = string.length()
        var start = 0
        for (index in 0 until length) {
            val replacement = getReplacementChars(string.charAt(index))
            if (replacement != null) {
                writer.write(string, start, index - start)
                writer.write(replacement)
                start = index + 1
            }
        }
        writer.write(string, start, length - start)
    }

    companion object {
        private const val CONTROL_CHARACTERS_END = 0x001f
        private val QUOT_CHARS = charArrayOf('\\', '"')
        private val BS_CHARS = charArrayOf('\\', '\\')
        private val LF_CHARS = charArrayOf('\\', 'n')
        private val CR_CHARS = charArrayOf('\\', 'r')
        private val TAB_CHARS = charArrayOf('\\', 't')

        // In JavaScript, U+2028 and U+2029 characters count as line endings and must
        // be encoded.
        // http://stackoverflow.com/questions/2965293/javascript-parse-error-on-u2028-unicode-character
        private val UNICODE_2028_CHARS = charArrayOf('\\', 'u', '2',
                '0', '2', '8')
        private val UNICODE_2029_CHARS = charArrayOf('\\', 'u', '2',
                '0', '2', '9')
        private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'a', 'b',
                'c', 'd', 'e', 'f')

        private fun getReplacementChars(ch: Char): CharArray? {
            if (ch > '\\') {
                if (ch < '\u2028' || ch > '\u2029') {
                    // The lower range contains 'a' .. 'z'. Only 2 checks required.
                    return null
                }
                return if (ch == '\u2028') UNICODE_2028_CHARS else UNICODE_2029_CHARS
            }
            if (ch == '\\') {
                return BS_CHARS
            }
            if (ch > '"') {
                // This range contains '0' .. '9' and 'A' .. 'Z'. Need 3 checks to get
                // here.
                return null
            }
            if (ch == '"') {
                return QUOT_CHARS
            }
            if (ch.code > CONTROL_CHARACTERS_END) {
                return null
            }
            if (ch == '\n') {
                return LF_CHARS
            }
            if (ch == '\r') {
                return CR_CHARS
            }
            return if (ch == '\t') {
                TAB_CHARS
            } else charArrayOf('\\',
                    'u',
                    '0',
                    '0',
                    HEX_DIGITS[ch.code shr 4 and 0x000f],
                    HEX_DIGITS[ch.code and 0x000f])
        }
    }
}