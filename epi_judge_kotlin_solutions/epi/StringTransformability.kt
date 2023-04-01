package epi

import epi.test_framework.EpiTest

object StringTransformability {
    @EpiTest(testDataFile = "string_transformability.tsv") // Uses BFS to find the least steps of transformation.
    fun transformString(D: Set<String?>?, s: String, t: String?): Int {
        val visited: Set<String> = HashSet<String>(D)
        val q: Queue<StringWithDistance> = ArrayDeque()
        visited.remove(s) // Marks s as visited by erasing it.
        q.add(StringWithDistance(s, 0))
        var f: StringWithDistance
        while (q.poll().also { f = it } != null) {
            // Returns if we find a match.
            if (f.candidateString.equals(t)) {
                return f.distance // Number of steps reaches t.
            }

            // Tries all possible transformations of f.candidateString.
            val str = f.candidateString
            for (i in 0 until str.length()) {
                val strStart = if (i == 0) "" else str.substring(0, i)
                val strEnd = if (i + 1 < str.length()) str.substring(i + 1) else ""
                var c = 'a'
                while (c <= 'z') {
                    // Iterates through 'a' ~ 'z'.
                    val modStr = strStart + c + strEnd
                    if (visited.contains(modStr)) {
                        visited.remove(modStr)
                        q.add(StringWithDistance(modStr, f.distance + 1))
                    }
                    ++c
                }
            }
        }
        return -1 // Cannot find a possible transformations.
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringTransformability.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class StringWithDistance(var candidateString: String, distance: Integer) {
        var distance: Integer

        init {
            this.distance = distance
        }
    }
}