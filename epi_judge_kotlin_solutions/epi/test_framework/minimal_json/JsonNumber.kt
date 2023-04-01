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
@SuppressWarnings("serial")
internal class JsonNumber(string: String?) : JsonValue() {
    private val string: String

    init {
        if (string == null) {
            throw NullPointerException("string is null")
        }
        this.string = string
    }

    @Override
    override fun toString(): String {
        return string
    }

    @Override
    @Throws(IOException::class)
    fun write(writer: JsonWriter) {
        writer.writeNumber(string)
    }

    @get:Override
    override val isNumber: Boolean
        get() = true

    @Override
    override fun asInt(): Int {
        return Integer.parseInt(string, 10)
    }

    @Override
    override fun asLong(): Long {
        return Long.parseLong(string, 10)
    }

    @Override
    override fun asFloat(): Float {
        return Float.parseFloat(string)
    }

    @Override
    override fun asDouble(): Double {
        return Double.parseDouble(string)
    }

    @Override
    override fun hashCode(): Int {
        return string.hashCode()
    }

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
        val other = `object` as JsonNumber
        return string.equals(other.string)
    }
}