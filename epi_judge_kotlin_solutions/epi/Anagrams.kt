package epi

import epi.test_framework.EpiTest

object Anagrams {
    @EpiTest(testDataFile = "anagrams.tsv")
    fun findAnagrams(dictionary: List<String>): List<List<String>> {
        val sortedStringToAnagrams: Map<String, List<String>> = HashMap()
        for (s in dictionary) {
            // Sorts the string, uses it as a key, and then appends
            // the original string as another value in the hash table.
            val sortedStr: String = Stream.of(s.split("")).sorted().collect(Collectors.joining())
            sortedStringToAnagrams.putIfAbsent(sortedStr, ArrayList<String>())
            sortedStringToAnagrams[sortedStr].add(s)
        }
        return sortedStringToAnagrams.values()
                .stream()
                .filter { group -> group.size() >= 2 }
                .collect(Collectors.toList())
    }

    @EpiTestComparator
    fun comp(expected: List<List<String?>?>,
             result: List<List<String?>?>?): Boolean {
        if (result == null) {
            return false
        }
        for (l in expected) {
            Collections.sort(l)
        }
        expected.sort(LexicographicalListComparator())
        for (l in result) {
            Collections.sort(l)
        }
        result.sort(LexicographicalListComparator())
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Anagrams.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}