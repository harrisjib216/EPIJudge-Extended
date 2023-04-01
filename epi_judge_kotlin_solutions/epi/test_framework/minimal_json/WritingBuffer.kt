package epi.test_framework.minimal_json

import java.io.IOException

/**
 * A lightweight writing buffer to reduce the amount of write operations to be
 * performed on the
 * underlying writer. This implementation is not thread-safe. It deliberately
 * deviates from the
 * contract of Writer. In particular, it does not flush or close the wrapped
 * writer nor does it
 * ensure that the wrapped writer is open.
 */
internal class WritingBuffer(writer: Writer, bufferSize: Int) : Writer() {
    private val writer: Writer
    private val buffer: CharArray
    private var fill = 0

    constructor(writer: Writer) : this(writer, 16) {}

    init {
        this.writer = writer
        buffer = CharArray(bufferSize)
    }

    @Override
    @Throws(IOException::class)
    fun write(c: Int) {
        if (fill > buffer.size - 1) {
            flush()
        }
        buffer[fill++] = c.toChar()
    }

    @Override
    @Throws(IOException::class)
    fun write(cbuf: CharArray?, off: Int, len: Int) {
        if (fill > buffer.size - len) {
            flush()
            if (len > buffer.size) {
                writer.write(cbuf, off, len)
                return
            }
        }
        System.arraycopy(cbuf, off, buffer, fill, len)
        fill += len
    }

    @Override
    @Throws(IOException::class)
    fun write(str: String, off: Int, len: Int) {
        if (fill > buffer.size - len) {
            flush()
            if (len > buffer.size) {
                writer.write(str, off, len)
                return
            }
        }
        str.getChars(off, off + len, buffer, fill)
        fill += len
    }

    /**
     * Flushes the internal buffer but does not flush the wrapped writer.
     */
    @Override
    @Throws(IOException::class)
    fun flush() {
        writer.write(buffer, 0, fill)
        fill = 0
    }

    /**
     * Does not close or flush the wrapped writer.
     */
    @Override
    @Throws(IOException::class)
    fun close() {
    }
}