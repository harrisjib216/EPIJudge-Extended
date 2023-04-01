package epi

import epi.test_framework.EpiTest

object ValidIpAddresses {
    @EpiTest(testDataFile = "valid_ip_addresses.tsv")
    fun getValidIpAddress(s: String?): List<String>? {
        // TODO - you fill in here.
        return null
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