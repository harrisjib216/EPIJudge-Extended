package epi

import epi.test_framework.EpiTest

class LruCache internal constructor(capacity: Int) {
    var isbnToPrice: LinkedHashMap<Integer, Integer>

    init {
        isbnToPrice = object : LinkedHashMap<Integer?, Integer?>(capacity, 1.0f, true) {
            @Override
            protected fun removeEldestEntry(e: Entry<Integer?, Integer?>?): Boolean {
                return this.size() > capacity
            }
        }
    }

    fun lookup(key: Integer?): Integer {
        return isbnToPrice.getOrDefault(key, -1)
    }

    fun insert(key: Integer?, value: Integer?) {

        // We add the value for key only if key is not present - we don't update
        // existing values.
        isbnToPrice.putIfAbsent(key, value)
    }

    fun erase(key: Object?): Boolean {
        return isbnToPrice.remove(key) != null
    }

    @EpiUserType(ctorParams = [String::class, Int::class, Int::class])
    class Op(var code: String, var arg1: Int, var arg2: Int)
    companion object {
        @EpiTest(testDataFile = "lru_cache.tsv")
        @Throws(TestFailure::class)
        fun lruCacheTester(commands: List<Op>) {
            if (commands.isEmpty() || !commands[0].code.equals("LruCache")) {
                throw RuntimeException("Expected LruCache as first command")
            }
            val cache = LruCache(commands[0].arg1)
            for (op in commands.subList(1, commands.size())) {
                var result: Int
                when (op.code) {
                    "lookup" -> {
                        result = cache.lookup(op.arg1)
                        if (result != op.arg2) {
                            throw TestFailure("Lookup: expected " + String.valueOf(op.arg2) +
                                    ", got " + String.valueOf(result))
                        }
                    }
                    "insert" -> cache.insert(op.arg1, op.arg2)
                    "erase" -> {
                        result = if (cache.erase(op.arg1)) 1 else 0
                        if (result != op.arg2) {
                            throw TestFailure("Erase: expected " + String.valueOf(op.arg2) +
                                    ", got " + String.valueOf(result))
                        }
                    }
                    else -> throw RuntimeException("Unexpected command " + op.code)
                }
            }
        }

        fun main(args: Array<String?>?) {
            System.exit(
                    GenericTest
                            .runFromAnnotations(args, "LruCache.java",
                                    object : Object() {}.getClass().getEnclosingClass())
                            .ordinal())
        }
    }
}