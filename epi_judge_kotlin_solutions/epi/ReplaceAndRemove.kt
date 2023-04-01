package epi

import epi.test_framework.EpiTest

object ReplaceAndRemove {
    fun replaceAndRemove(size: Int, s: CharArray): Int {

        // Forward iteration: remove "b"s and count the number of "a"s.
        var writeIdx = 0
        var aCount = 0
        for (i in 0 until size) {
            if (s[i] != 'b') {
                s[writeIdx++] = s[i]
            }
            if (s[i] == 'a') {
                ++aCount
            }
        }

        // Backward iteration: replace "a"s with "dd"s starting from the end.
        var curIdx = writeIdx - 1
        writeIdx = writeIdx + aCount - 1
        val finalSize = writeIdx + 1
        while (curIdx >= 0) {
            if (s[curIdx] == 'a') {
                s[writeIdx--] = 'd'
                s[writeIdx--] = 'd'
            } else {
                s[writeIdx--] = s[curIdx]
            }
            --curIdx
        }
        return finalSize
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