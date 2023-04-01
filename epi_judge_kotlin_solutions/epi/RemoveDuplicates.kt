package epi

import epi.test_framework.EpiTest

object RemoveDuplicates {
    fun eliminateDuplicate(names: List<Name>) {
        Collections.sort(names) // Makes identical elements become neighbors.
        var writeIdx = 0
        for (i in 1 until names.size()) {
            if (!names[i].firstName.equals(names[writeIdx].firstName)) {
                names.set(++writeIdx, names[i])
            }
        }
        // Shrinks array size.
        names.subList(++writeIdx, names.size()).clear()
    }

    @EpiTest(testDataFile = "remove_duplicates.tsv")
    fun eliminateDuplicateWrapper(names: List<Name>): List<Name> {
        eliminateDuplicate(names)
        return names
    }

    @EpiTestComparator
    fun comp(expected: List<String>, result: List<Name>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        if (expected.size() !== result.size()) {
            return false
        }
        for (i in 0 until result.size()) {
            if (!expected[i].equals(result[i].firstName)) {
                return false
            }
        }
        return true
    }

    @EpiTestExpectedType
    var expectedType: List<String>? = null
    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RemoveDuplicates.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [String::class, String::class]) //@include
    class Name(var firstName: String, var lastName: String) : Comparable<Name?> {
        @Override
        override fun equals(obj: Object?): Boolean {
            if (obj == null || obj !is Name) {
                return false
            }
            if (this === obj) {
                return true
            }
            val name = obj as Name
            return firstName.equals(name.firstName) && lastName.equals(name.lastName)
        }

        @Override
        override fun toString(): String {
            return firstName
        }

        @Override
        operator fun compareTo(name: Name): Int {
            val cmpFirst = firstName.compareTo(name.firstName)
            return if (cmpFirst != 0) {
                cmpFirst
            } else lastName.compareTo(name.lastName)
        }
    }
}