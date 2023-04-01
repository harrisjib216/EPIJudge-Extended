package epi

import epi.test_framework.EpiTest

object IsStringInMatrix {
    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    fun isPatternContainedInGrid(grid: List<List<Integer>>,
                                 pattern: List<Integer>): Boolean {
        for (i in 0 until grid.size()) {
            for (j in 0 until grid[i].size()) {
                if (isPatternSuffixContainedStartingAtXY(
                                grid, i, j, pattern,  /*offset=*/0, HashSet())) {
                    return true
                }
            }
        }
        return false
    }

    // Each entry in previousAttempts is a point in the grid and suffix of pattern
    // (identified by its offset). Presence in previousAttempts indicates the
    // suffix is not contained in the grid starting from that point.
    private fun isPatternSuffixContainedStartingAtXY(grid: List<List<Integer>>, x: Int, y: Int,
                                                     pattern: List<Integer>, offset: Int,
                                                     previousAttempts: Set<Attempt>): Boolean {
        if (pattern.size() === offset) {
            // Nothing left to complete.
            return true
        }
        // Early return if (x, y) lies outside the grid or the character does not
        // match or we have already tried this combination.
        if (x < 0 || x >= grid.size() || y < 0 || y >= grid[x].size() ||
                previousAttempts.contains(Attempt(x, y, offset)) ||
                !grid[x][y].equals(pattern[offset])) {
            return false
        }
        for (nextXY in List.of(List.of(x - 1, y), List.of(x + 1, y),
                List.of(x, y - 1), List.of(x, y + 1))) {
            if (isPatternSuffixContainedStartingAtXY(grid, nextXY[0],
                            nextXY[1], pattern,
                            offset + 1, previousAttempts)) {
                return true
            }
        }
        previousAttempts.add(Attempt(x, y, offset))
        return false
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringInMatrix.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class Attempt(x: Integer?, y: Integer?, offset: Integer?) {
        var x: Integer?
        var y: Integer?
        var offset: Integer?

        init {
            this.x = x
            this.y = y
            this.offset = offset
        }

        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val cacheEntry = o as Attempt
            if (if (x != null) !x.equals(cacheEntry.x) else cacheEntry.x != null) {
                return false
            }
            if (if (y != null) !y.equals(cacheEntry.y) else cacheEntry.y != null) {
                return false
            }
            return if (if (offset != null) !offset.equals(cacheEntry.offset) else cacheEntry.offset != null) {
                false
            } else true
        }

        @Override
        override fun hashCode(): Int {
            return Objects.hash(x, y, offset)
        }
    }
}