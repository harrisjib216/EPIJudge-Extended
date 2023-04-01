package epi

import epi.test_framework.EpiTest

object SpreadsheetEncoding {
    @EpiTest(testDataFile = "spreadsheet_encoding.tsv")
    fun ssDecodeColID(col: String?): Int {
        // TODO - you fill in here.
        return 0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}