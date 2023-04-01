package epi

import epi.test_framework.EpiTest

object DirectoryPathNormalization {
    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    fun shortestEquivalentPath(path: String): String {
        if (path.equals("")) {
            throw IllegalArgumentException("Empty string is not a legal path.")
        }
        val pathNames: Deque<String> = ArrayDeque()
        // Special case: starts with "/", which is an absolute path.
        if (path.startsWith("/")) {
            pathNames.addFirst("/")
        }
        for (token in path.split("/")) {
            if (token.equals("..")) {
                if (pathNames.isEmpty() || pathNames.peekFirst().equals("..")) {
                    pathNames.addFirst(token)
                } else {
                    if (pathNames.peekFirst().equals("/")) {
                        throw IllegalArgumentException(
                                "Path error, trying to go up root $path")
                    }
                    pathNames.removeFirst()
                }
            } else if (!token.equals(".") && !token.isEmpty()) { // Must be a name.
                pathNames.addFirst(token)
            }
        }
        val result = StringBuilder()
        if (!pathNames.isEmpty()) {
            val it: Iterator<String> = pathNames.descendingIterator()
            var prev = it.next()
            result.append(prev)
            while (it.hasNext()) {
                if (!prev.equals("/")) {
                    result.append("/")
                }
                prev = it.next()
                result.append(prev)
            }
        }
        return result.toString()
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}