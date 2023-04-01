package epi

import epi.test_framework.EpiTest

object SpreadsheetEncoding {
    @EpiTest(testDataFile = "spreadsheet_encoding.tsv")
    fun ssDecodeColID(col: String): Int {
        return col.chars().reduce(0) { result, c -> result * 26 + c - 'A'.code + 1 }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}