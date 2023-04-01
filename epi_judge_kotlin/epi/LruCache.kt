package epi

import epi.test_framework.EpiTest

class LruCache internal constructor(capacity: Int) {
    fun lookup(key: Integer?): Integer {
        // TODO - you fill in here.
        return 0
    }

    fun insert(key: Integer?, value: Integer?) {
        // TODO - you fill in here.
        return
    }

    fun erase(key: Object?): Boolean {
        // TODO - you fill in here.
        return true
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