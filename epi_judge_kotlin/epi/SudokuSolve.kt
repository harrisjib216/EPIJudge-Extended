package epi

import epi.test_framework.EpiTest

object SudokuSolve {
    fun solveSudoku(partialAssignment: List<List<Integer?>?>?): Boolean {
        // TODO - you fill in here.
        return true
    }

    @EpiTest(testDataFile = "sudoku_solve.tsv")
    @Throws(Exception::class)
    fun solveSudokuWrapper(executor: TimedExecutor,
                           partialAssignment: List<List<Integer>>) {
        val solved: List<List<Integer>> = ArrayList()
        for (row in partialAssignment) {
            solved.add(ArrayList(row))
        }
        executor.run { solveSudoku(solved) }
        if (partialAssignment.size() !== solved.size()) {
            throw TestFailure("Initial cell assignment has been changed")
        }
        for (i in 0 until partialAssignment.size()) {
            val br: List<Integer> = partialAssignment[i]
            val sr: List<Integer> = solved[i]
            if (br.size() !== sr.size()) {
                throw TestFailure("Initial cell assignment has been changed")
            }
            for (j in 0 until br.size()) if (br[j] !== 0 && !Objects.equals(br[j], sr[j])) throw TestFailure("Initial cell assignment has been changed")
        }
        val blockSize = Math.sqrt(solved.size()) as Int
        for (i in 0 until solved.size()) {
            assertUniqueSeq(solved[i])
            assertUniqueSeq(gatherColumn(solved, i))
            assertUniqueSeq(gatherSquareBlock(solved, blockSize, i))
        }
    }

    @Throws(TestFailure::class)
    private fun assertUniqueSeq(seq: List<Integer>) {
        val seen: Set<Integer> = HashSet()
        for (x in seq) {
            if (x == 0) {
                throw TestFailure("Cell left uninitialized")
            }
            if (x < 0 || x > seq.size()) {
                throw TestFailure("Cell value out of range")
            }
            if (seen.contains(x)) {
                throw TestFailure("Duplicate value in section")
            }
            seen.add(x)
        }
    }

    private fun gatherColumn(data: List<List<Integer>>, i: Int): List<Integer> {
        val result: List<Integer> = ArrayList()
        for (row in data) {
            result.add(row[i])
        }
        return result
    }

    private fun gatherSquareBlock(data: List<List<Integer>>,
                                  blockSize: Int, n: Int): List<Integer> {
        val result: List<Integer> = ArrayList()
        val blockX = n % blockSize
        val blockY = n / blockSize
        for (i in (blockX * blockSize until blockX + 1) * blockSize) {
            for (j in (blockY * blockSize until blockY + 1) * blockSize) {
                result.add(data[i][j])
            }
        }
        return result
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SudokuSolve.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}