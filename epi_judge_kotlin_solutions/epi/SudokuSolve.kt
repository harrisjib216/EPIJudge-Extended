package epi

import epi.test_framework.EpiTest

object SudokuSolve {
    private const val EMPTY_ENTRY = 0
    fun solveSudoku(partialAssignment: List<List<Integer>>): Boolean {
        return solvePartialSudoku(0, 0, partialAssignment)
    }

    private fun solvePartialSudoku(i: Int, j: Int, partialAssignment: List<List<Integer>>): Boolean {
        var i = i
        var j = j
        if (i == partialAssignment.size()) {
            i = 0 // Starts a new row.
            if (++j == partialAssignment[i].size()) {
                return true // Entire matrix has been filled without conflict.
            }
        }

        // Skips nonempty entries.
        if (partialAssignment[i][j] !== EMPTY_ENTRY) {
            return solvePartialSudoku(i + 1, j, partialAssignment)
        }
        for (`val` in 1..partialAssignment.size()) {
            // It's substantially quicker to check if entry val conflicts
            // with any of the constraints if we add it at (i,j) before
            // adding it, rather than adding it and then checking all constraints.
            // The reason is that we are starting with a valid configuration,
            // and the only entry which can cause a problem is entry val at (i,j).
            if (validToAddVal(partialAssignment, i, j, `val`)) {
                partialAssignment[i].set(j, `val`)
                if (solvePartialSudoku(i + 1, j, partialAssignment)) {
                    return true
                }
            }
        }
        partialAssignment[i].set(j, EMPTY_ENTRY) // Undo assignment.
        return false
    }

    private fun validToAddVal(partialAssignment: List<List<Integer>>,
                              i: Int, j: Int, `val`: Int): Boolean {
        // Check row constraints.
        if (partialAssignment.stream().anyMatch { row -> row.get(j) === `val` }) {
            return false
        }

        // Check column constraints.
        if (partialAssignment[i].contains(`val`)) {
            return false
        }

        // Check region constraints.
        val regionSize = Math.sqrt(partialAssignment.size()) as Int
        val I = i / regionSize
        val J = j / regionSize
        for (a in 0 until regionSize) {
            for (b in 0 until regionSize) {
                if (`val` ==
                        partialAssignment[regionSize * I + a][regionSize * J + b]) {
                    return false
                }
            }
        }
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