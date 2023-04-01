package epi.test_framework.minimal_json

import java.io.IOException

/**
 * Enables human readable JSON output by inserting whitespace between
 * values.after commas and
 * colons. Example:
 *
 * <pre>
 * jsonValue.writeTo(writer, PrettyPrint.singleLine());
</pre> *
 */
internal class PrettyPrint protected constructor(private val indentChars: CharArray?) : WriterConfig() {
    @Override
    protected override fun createWriter(writer: Writer): JsonWriter {
        return PrettyPrintWriter(writer, indentChars)
    }

    private class PrettyPrintWriter(writer: Writer, private val indentChars: CharArray?) : JsonWriter(writer) {
        private var indent = 0
        @Override
        @Throws(IOException::class)
        protected override fun writeArrayOpen() {
            indent++
            writer.write('[')
            writeNewLine()
        }

        @Override
        @Throws(IOException::class)
        protected override fun writeArrayClose() {
            indent--
            writeNewLine()
            writer.write(']')
        }

        @Override
        @Throws(IOException::class)
        protected override fun writeArraySeparator() {
            writer.write(',')
            if (!writeNewLine()) {
                writer.write(' ')
            }
        }

        @Override
        @Throws(IOException::class)
        protected override fun writeObjectOpen() {
            indent++
            writer.write('{')
            writeNewLine()
        }

        @Override
        @Throws(IOException::class)
        protected override fun writeObjectClose() {
            indent--
            writeNewLine()
            writer.write('}')
        }

        @Override
        @Throws(IOException::class)
        protected override fun writeMemberSeparator() {
            writer.write(':')
            writer.write(' ')
        }

        @Override
        @Throws(IOException::class)
        protected override fun writeObjectSeparator() {
            writer.write(',')
            if (!writeNewLine()) {
                writer.write(' ')
            }
        }

        @Throws(IOException::class)
        private fun writeNewLine(): Boolean {
            if (indentChars == null) {
                return false
            }
            writer.write('\n')
            for (i in 0 until indent) {
                writer.write(indentChars)
            }
            return true
        }
    }

    companion object {
        /**
         * Print every value on a separate line. Use tabs (`\t`) for
         * indentation.
         *
         * @return A PrettyPrint instance for wrapped mode with tab indentation
         */
        fun singleLine(): PrettyPrint {
            return PrettyPrint(null)
        }

        /**
         * Print every value on a separate line. Use the given number of spaces for
         * indentation.
         *
         * @param number
         * the number of spaces to use
         * @return A PrettyPrint instance for wrapped mode with spaces indentation
         */
        fun indentWithSpaces(number: Int): PrettyPrint {
            if (number < 0) {
                throw IllegalArgumentException("number is negative")
            }
            val chars = CharArray(number)
            Arrays.fill(chars, ' ')
            return PrettyPrint(chars)
        }

        /**
         * Do not break lines, but still insert whitespace between values.
         *
         * @return A PrettyPrint instance for single-line mode
         */
        fun indentWithTabs(): PrettyPrint {
            return PrettyPrint(charArrayOf('\t'))
        }
    }
}