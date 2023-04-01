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
internal class JsonLiteral(private val value: String) : JsonValue() {
    @get:Override
    override val isNull: Boolean

    @get:Override
    override val isTrue: Boolean

    @get:Override
    override val isFalse: Boolean

    init {
        isNull = "null".equals(value)
        isTrue = "true".equals(value)
        isFalse = "false".equals(value)
    }

    @Override
    @Throws(IOException::class)
    fun write(writer: JsonWriter) {
        writer.writeLiteral(value)
    }

    @Override
    override fun toString(): String {
        return value
    }

    @Override
    override fun hashCode(): Int {
        return value.hashCode()
    }

    @get:Override
    override val isBoolean: Boolean
        get() = isTrue || isFalse

    @Override
    override fun asBoolean(): Boolean {
        return if (isNull) super.asBoolean() else isTrue
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
        val other = `object` as JsonLiteral
        return value.equals(other.value)
    }
}