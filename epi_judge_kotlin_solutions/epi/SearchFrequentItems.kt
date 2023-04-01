package epi

import epi.test_framework.EpiTest

object SearchFrequentItems {
    fun searchFrequentItems(k: Int,
                            stream: Iterable<String?>): List<String> {

        // Finds the candidates which may occur > n / k times.
        var buf = ""
        var table: Map<String?, Integer?> = HashMap()
        var n = 0 // Counts the number of strings.
        var sequence = stream.iterator()
        while (sequence.hasNext()) {
            buf = sequence.next()
            table.put(buf, table.getOrDefault(buf, 0) + 1)
            ++n
            // Detecting k items in table, at least one of them must have exactly one
            // in it. We will discard those k items by one for each.
            if (table.size() === k) {
                val delKeys: List<String> = ArrayList()
                for (entry in table.entrySet()) {
                    if (entry.getValue() - 1 === 0) {
                        delKeys.add(entry.getKey())
                    } else {
                        table.put(entry.getKey(), entry.getValue() - 1)
                    }
                }
                for (s in delKeys) {
                    table.remove(s)
                }
            }
        }

        // Resets table for the following counting.
        table = table.entrySet().stream().collect(
                Collectors.toMap({ e -> e.getKey() }) { e -> 0 })

        // Counts the occurrence of each candidate word.
        sequence = stream.iterator()
        while (sequence.hasNext()) {
            buf = sequence.next()
            val it: Integer? = table[buf]
            if (it != null) {
                table.put(buf, it + 1)
            }
        }
        val threshold = n.toDouble() / k
        // Selects the word which occurs > n / k times.
        return table.entrySet()
                .stream()
                .filter { it -> threshold < it.getValue() as Double }
                .map { it -> it.getKey() }
                .collect(Collectors.toList())
    }

    @EpiTest(testDataFile = "search_frequent_items.tsv")
    fun searchFrequentItemsWrapper(k: Int,
                                   stream: List<String?>): List<String> {
        return searchFrequentItems(k, stream)
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
                        .runFromAnnotations(args, "SearchFrequentItems.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}