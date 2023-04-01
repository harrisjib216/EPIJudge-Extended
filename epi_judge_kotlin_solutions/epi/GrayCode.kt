package epi

import epi.test_framework.EpiTest

object GrayCode {
    fun grayCode(numBits: Int): List<Integer> {
        val result: List<Integer> = ArrayList(List.of(0))
        directedGrayCode(numBits, HashSet<Integer>(List.of(0)), result)
        return result
    }

    private fun directedGrayCode(numBits: Int, history: Set<Integer>,
                                 result: List<Integer>): Boolean {
        if (result.size() === 1 shl numBits) {
            return differsByOneBit(result[0], result[result.size() - 1])
        }
        for (i in 0 until numBits) {
            val previousCode: Int = result[result.size() - 1]
            val candidateNextCode = previousCode xor (1 shl i)
            if (!history.contains(candidateNextCode)) {
                history.add(candidateNextCode)
                result.add(candidateNextCode)
                if (directedGrayCode(numBits, history, result)) {
                    return true
                }
                result.remove(result.size() - 1)
                history.remove(candidateNextCode)
            }
        }
        return false
    }

    private fun differsByOneBit(x: Int, y: Int): Boolean {
        val bitDifference = x xor y
        return bitDifference != 0 && bitDifference and bitDifference - 1 == 0
    }

    @EpiTest(testDataFile = "gray_code.tsv")
    @Throws(Exception::class)
    fun grayCodeWrapper(executor: TimedExecutor, numBits: Int) {
        val result: List<Integer> = executor.run { grayCode(numBits) }
        val expectedSize = 1 shl numBits
        if (result.size() !== expectedSize) {
            throw TestFailure("Length mismatch: expected " +
                    String.valueOf(expectedSize) + ", got " +
                    String.valueOf(result.size()))
        }
        for (i in 1 until result.size()) if (!differsByOneBit(result[i - 1], result[i])) {
            if (result[i - 1].equals(result[i])) {
                throw TestFailure("Two adjacent entries are equal")
            } else {
                throw TestFailure(
                        "Two adjacent entries differ by more than 1 bit")
            }
        }
        val uniq: Set<Integer> = HashSet(result)
        if (uniq.size() !== result.size()) {
            throw TestFailure("Not all entries are distinct: found " +
                    String.valueOf(result.size() - uniq.size()) +
                    " duplicates")
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "GrayCode.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}