package epi

import epi.test_framework.EpiTest

object ReplaceAndRemove {
    fun replaceAndRemove(size: Int, s: CharArray?): Int {
        // TODO - you fill in here.
        return 0
    }

    @EpiTest(testDataFile = "replace_and_remove.tsv")
    @Throws(Exception::class)
    fun replaceAndRemoveWrapper(executor: TimedExecutor, size: Integer, s: List<String>): List<String> {
        val sCopy = CharArray(s.size())
        for (i in 0 until size) {
            if (!s[i].isEmpty()) {
                sCopy[i] = s[i].charAt(0)
            }
        }
        val resSize: Integer = executor.run { replaceAndRemove(size, sCopy) }
        val result: List<String> = ArrayList()
        for (i in 0 until resSize) {
            result.add(Character.toString(sCopy[i]))
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReplaceAndRemove.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}