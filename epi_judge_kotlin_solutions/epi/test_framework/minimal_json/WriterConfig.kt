package epi.test_framework.minimal_json

import java.io.Writer

/**
 * Controls the formatting of the JSON output. Use one of the available
 * constants.
 */
abstract class WriterConfig {
    abstract fun createWriter(writer: Writer?): JsonWriter?

    companion object {
        /**
         * Write JSON in its minimal form, without any additional whitespace. This is
         * the default.
         */
        var MINIMAL: WriterConfig = object : WriterConfig() {
            @Override
            override fun createWriter(writer: Writer): JsonWriter {
                return JsonWriter(writer)
            }
        }

        /**
         * Write JSON in pretty-print, with each value on a separate line and an
         * indentation of two
         * spaces.
         */
        var PRETTY_PRINT: WriterConfig = PrettyPrint.indentWithSpaces(4)
    }
}