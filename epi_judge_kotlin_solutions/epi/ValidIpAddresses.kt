package epi

import epi.test_framework.EpiTest

object ValidIpAddresses {
    @EpiTest(testDataFile = "valid_ip_addresses.tsv")
    fun getValidIpAddress(s: String): List<String> {
        val result: List<String> = ArrayList()
        var i = 1
        while (i < 4 && i < s.length()) {
            val first: String = s.substring(0, i)
            if (isValidPart(first)) {
                var j = 1
                while (i + j < s.length() && j < 4) {
                    val second: String = s.substring(i, i + j)
                    if (isValidPart(second)) {
                        var k = 1
                        while (i + j + k < s.length() && k < 4) {
                            val third: String = s.substring(i + j, i + j + k)
                            val fourth: String = s.substring(i + j + k)
                            if (isValidPart(third) && isValidPart(fourth)) {
                                result.add("$first.$second.$third.$fourth")
                            }
                            ++k
                        }
                    }
                    ++j
                }
            }
            ++i
        }
        return result
    }

    private fun isValidPart(s: String): Boolean {
        if (s.length() > 3) {
            return false
        }
        // "00", "000", "01", etc. are not valid, but "0" is valid.
        if (s.startsWith("0") && s.length() > 1) {
            return false
        }
        val `val`: Int = Integer.parseInt(s)
        return `val` <= 255 && `val` >= 0
    }

    @EpiTestComparator
    fun comp(expected: List<String?>, result: List<String?>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ValidIpAddresses.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}