package epi.test_framework.minimal_json

import java.io.IOException

/**
 * A streaming parser for JSON text. The parser reports all events to a given
 * handler.
 */
class JsonParser @SuppressWarnings("unchecked") constructor(handler: JsonHandler<*, *>?) {
    private val handler: JsonHandler<Object, Object>
    private var reader: Reader? = null
    private var buffer: CharArray
    private var bufferOffset = 0
    private var index = 0
    private var fill = 0
    private var line = 0
    private var lineOffset = 0
    private var current = 0
    private var captureBuffer: StringBuilder? = null
    private var captureStart = 0
    private var nestingLevel = 0
    /*
   * |                      bufferOffset
   *                        v
   * [a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t]        < input
   *                       [l|m|n|o|p|q|r|s|t|?|?]    < buffer
   *                          ^               ^
   *                       |  index           fill
   */
    /**
     * Creates a new JsonParser with the given handler. The parser will report all
     * parser events to
     * this handler.
     *
     * @param handler
     * the handler to process parser events
     */
    init {
        if (handler == null) {
            throw NullPointerException("handler is null")
        }
        this.handler = handler
        handler.parser = this
    }

    /**
     * Parses the given input string. The input must contain a valid JSON value,
     * optionally padded
     * with whitespace.
     *
     * @param string
     * the input string, must be valid JSON
     * @throws ParseException
     * if the input is not valid JSON
     */
    fun parse(string: String?) {
        if (string == null) {
            throw NullPointerException("string is null")
        }
        val bufferSize: Int = Math.max(MIN_BUFFER_SIZE,
                Math.min(DEFAULT_BUFFER_SIZE, string.length()))
        try {
            parse(StringReader(string), bufferSize)
        } catch (exception: IOException) {
            // StringReader does not throw IOException
            throw RuntimeException(exception)
        }
    }

    /**
     * Reads the entire input from the given reader and parses it as JSON. The
     * input must contain a
     * valid JSON value, optionally padded with whitespace.
     *
     *
     * Characters are read in chunks into a default-sized input buffer. Hence,
     * wrapping a reader in an
     * additional `BufferedReader` likely won't improve reading
     * performance.
     *
     *
     * @param reader
     * the reader to read the input from
     * @throws IOException
     * if an I/O error occurs in the reader
     * @throws ParseException
     * if the input is not valid JSON
     */
    @Throws(IOException::class)
    fun parse(reader: Reader?) {
        parse(reader, DEFAULT_BUFFER_SIZE)
    }

    /**
     * Reads the entire input from the given reader and parses it as JSON. The
     * input must contain a
     * valid JSON value, optionally padded with whitespace.
     *
     *
     * Characters are read in chunks into an input buffer of the given size.
     * Hence, wrapping a reader
     * in an additional `BufferedReader` likely won't improve reading
     * performance.
     *
     *
     * @param reader
     * the reader to read the input from
     * @param buffersize
     * the size of the input buffer in chars
     * @throws IOException
     * if an I/O error occurs in the reader
     * @throws ParseException
     * if the input is not valid JSON
     */
    @Throws(IOException::class)
    fun parse(reader: Reader?, buffersize: Int) {
        if (reader == null) {
            throw NullPointerException("reader is null")
        }
        if (buffersize <= 0) {
            throw IllegalArgumentException("buffersize is zero or negative")
        }
        this.reader = reader
        buffer = CharArray(buffersize)
        bufferOffset = 0
        index = 0
        fill = 0
        line = 1
        lineOffset = 0
        current = 0
        captureStart = -1
        read()
        skipWhiteSpace()
        readValue()
        skipWhiteSpace()
        if (!isEndOfText) {
            throw error("Unexpected character")
        }
    }

    @Throws(IOException::class)
    private fun readValue() {
        when (current) {
            'n' -> readNull()
            't' -> readTrue()
            'f' -> readFalse()
            '"' -> readString()
            '[' -> readArray()
            '{' -> readObject()
            '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> readNumber()
            else -> throw expected("value")
        }
    }

    @Throws(IOException::class)
    private fun readArray() {
        val array: Object = handler.startArray()
        read()
        if (++nestingLevel > MAX_NESTING_LEVEL) {
            throw error("Nesting too deep")
        }
        skipWhiteSpace()
        if (readChar(']')) {
            nestingLevel--
            handler.endArray(array)
            return
        }
        do {
            skipWhiteSpace()
            handler.startArrayValue(array)
            readValue()
            handler.endArrayValue(array)
            skipWhiteSpace()
        } while (readChar(','))
        if (!readChar(']')) {
            throw expected("',' or ']'")
        }
        nestingLevel--
        handler.endArray(array)
    }

    @Throws(IOException::class)
    private fun readObject() {
        val `object`: Object = handler.startObject()
        read()
        if (++nestingLevel > MAX_NESTING_LEVEL) {
            throw error("Nesting too deep")
        }
        skipWhiteSpace()
        if (readChar('}')) {
            nestingLevel--
            handler.endObject(`object`)
            return
        }
        do {
            skipWhiteSpace()
            handler.startObjectName(`object`)
            val name = readName()
            handler.endObjectName(`object`, name)
            skipWhiteSpace()
            if (!readChar(':')) {
                throw expected("':'")
            }
            skipWhiteSpace()
            handler.startObjectValue(`object`, name)
            readValue()
            handler.endObjectValue(`object`, name)
            skipWhiteSpace()
        } while (readChar(','))
        if (!readChar('}')) {
            throw expected("',' or '}'")
        }
        nestingLevel--
        handler.endObject(`object`)
    }

    @Throws(IOException::class)
    private fun readName(): String {
        if (current != '"'.code) {
            throw expected("name")
        }
        return readStringInternal()
    }

    @Throws(IOException::class)
    private fun readNull() {
        handler.startNull()
        read()
        readRequiredChar('u')
        readRequiredChar('l')
        readRequiredChar('l')
        handler.endNull()
    }

    @Throws(IOException::class)
    private fun readTrue() {
        handler.startBoolean()
        read()
        readRequiredChar('r')
        readRequiredChar('u')
        readRequiredChar('e')
        handler.endBoolean(true)
    }

    @Throws(IOException::class)
    private fun readFalse() {
        handler.startBoolean()
        read()
        readRequiredChar('a')
        readRequiredChar('l')
        readRequiredChar('s')
        readRequiredChar('e')
        handler.endBoolean(false)
    }

    @Throws(IOException::class)
    private fun readRequiredChar(ch: Char) {
        if (!readChar(ch)) {
            throw expected("'$ch'")
        }
    }

    @Throws(IOException::class)
    private fun readString() {
        handler.startString()
        handler.endString(readStringInternal())
    }

    @Throws(IOException::class)
    private fun readStringInternal(): String {
        read()
        startCapture()
        while (current != '"'.code) {
            if (current == '\\'.code) {
                pauseCapture()
                readEscape()
                startCapture()
            } else if (current < 0x20) {
                throw expected("valid string character")
            } else {
                read()
            }
        }
        val string = endCapture()
        read()
        return string
    }

    @Throws(IOException::class)
    private fun readEscape() {
        read()
        when (current) {
            '"', '/', '\\' -> captureBuffer.append(current.toChar())
            'b' -> captureBuffer.append('\b')
            'f' -> captureBuffer.append('\f')
            'n' -> captureBuffer.append('\n')
            'r' -> captureBuffer.append('\r')
            't' -> captureBuffer.append('\t')
            'u' -> {
                val hexChars = CharArray(4)
                var i = 0
                while (i < 4) {
                    read()
                    if (!isHexDigit) {
                        throw expected("hexadecimal digit")
                    }
                    hexChars[i] = current.toChar()
                    i++
                }
                captureBuffer.append(Integer.parseInt(String(hexChars), 16) as Char)
            }
            else -> throw expected("valid escape sequence")
        }
        read()
    }

    @Throws(IOException::class)
    private fun readNumber() {
        handler.startNumber()
        startCapture()
        readChar('-')
        val firstDigit = current
        if (!readDigit()) {
            throw expected("digit")
        }
        if (firstDigit != '0'.code) {
            while (readDigit()) {
            }
        }
        readFraction()
        readExponent()
        handler.endNumber(endCapture())
    }

    @Throws(IOException::class)
    private fun readFraction(): Boolean {
        if (!readChar('.')) {
            return false
        }
        if (!readDigit()) {
            throw expected("digit")
        }
        while (readDigit()) {
        }
        return true
    }

    @Throws(IOException::class)
    private fun readExponent(): Boolean {
        if (!readChar('e') && !readChar('E')) {
            return false
        }
        if (!readChar('+')) {
            readChar('-')
        }
        if (!readDigit()) {
            throw expected("digit")
        }
        while (readDigit()) {
        }
        return true
    }

    @Throws(IOException::class)
    private fun readChar(ch: Char): Boolean {
        if (current != ch.code) {
            return false
        }
        read()
        return true
    }

    @Throws(IOException::class)
    private fun readDigit(): Boolean {
        if (!isDigit) {
            return false
        }
        read()
        return true
    }

    @Throws(IOException::class)
    private fun skipWhiteSpace() {
        while (isWhiteSpace) {
            read()
        }
    }

    @Throws(IOException::class)
    private fun read() {
        if (index == fill) {
            if (captureStart != -1) {
                captureBuffer.append(buffer, captureStart, fill - captureStart)
                captureStart = 0
            }
            bufferOffset += fill
            fill = reader.read(buffer, 0, buffer.size)
            index = 0
            if (fill == -1) {
                current = -1
                index++
                return
            }
        }
        if (current == '\n'.code) {
            line++
            lineOffset = bufferOffset + index
        }
        current = buffer[index++].code
    }

    private fun startCapture() {
        if (captureBuffer == null) {
            captureBuffer = StringBuilder()
        }
        captureStart = index - 1
    }

    private fun pauseCapture() {
        val end = if (current == -1) index else index - 1
        captureBuffer.append(buffer, captureStart, end - captureStart)
        captureStart = -1
    }

    private fun endCapture(): String {
        val start = captureStart
        val end = index - 1
        captureStart = -1
        if (captureBuffer.length() > 0) {
            captureBuffer.append(buffer, start, end - start)
            val captured: String = captureBuffer.toString()
            captureBuffer.setLength(0)
            return captured
        }
        return String(buffer, start, end - start)
    }

    val location: epi.test_framework.minimal_json.Location
        get() {
            val offset = bufferOffset + index - 1
            val column = offset - lineOffset + 1
            return Location(offset, line, column)
        }

    private fun expected(expected: String): ParseException {
        return if (isEndOfText) {
            error("Unexpected end of input")
        } else error("Expected $expected")
    }

    private fun error(message: String): ParseException {
        return ParseException(message, location)
    }

    private val isWhiteSpace: Boolean
        private get() = current == ' '.code || current == '\t'.code || current == '\n'.code || current == '\r'.code
    private val isDigit: Boolean
        private get() = current >= '0'.code && current <= '9'.code
    private val isHexDigit: Boolean
        private get() = current >= '0'.code && current <= '9'.code || current >= 'a'.code && current <= 'f'.code || current >= 'A'.code && current <= 'F'.code
    private val isEndOfText: Boolean
        private get() = current == -1

    companion object {
        private const val MAX_NESTING_LEVEL = 1000
        private const val MIN_BUFFER_SIZE = 10
        private const val DEFAULT_BUFFER_SIZE = 1024
    }
}