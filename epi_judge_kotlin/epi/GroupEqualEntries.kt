package epi

import epi.test_framework.EpiTest

object GroupEqualEntries {
    fun groupByAge(people: List<Person?>?) {
        // TODO - you fill in here.
        return
    }

    private fun buildMultiset(people: List<Person>): Map<Person, Integer> {
        val m: Map<Person, Integer> = HashMap()
        for (p in people) {
            m.put(p, m.getOrDefault(p, 0) + 1)
        }
        return m
    }

    @EpiTest(testDataFile = "group_equal_entries.tsv")
    @Throws(Exception::class)
    fun groupByAgeWrapper(executor: TimedExecutor,
                          people: List<Person>) {
        if (people.isEmpty()) {
            return
        }
        val values: Map<Person, Integer> = buildMultiset(people)
        executor.run { groupByAge(people) }
        val newValues: Map<Person, Integer> = buildMultiset(people)
        if (!values.equals(newValues)) {
            throw TestFailure("Entry set changed")
        }
        val ages: Set<Integer> = HashSet()
        var lastAge: Int = people[0].age
        for (p in people) {
            if (ages.contains(p.age)) {
                throw TestFailure("Entries are not grouped by age")
            }
            if (p.age != lastAge) {
                ages.add(lastAge)
                lastAge = p.age
            }
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "GroupEqualEntries.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Integer::class, String::class])
    class Person(k: Integer, n: String) {
        var age: Integer
        var name: String

        init {
            age = k
            name = n
        }

        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) return true
            if (o == null || getClass() !== o.getClass()) return false
            val person = o as Person
            return if (!age.equals(person.age)) false else name.equals(person.name)
        }

        @Override
        override fun hashCode(): Int {
            var result: Int = age.hashCode()
            result = 31 * result + name.hashCode()
            return result
        }
    }
}