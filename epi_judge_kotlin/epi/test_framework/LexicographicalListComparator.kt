package epi.test_framework

import java.util.Comparator

class LexicographicalListComparator<T : Comparable<T>?> : Comparator<List<T>?> {
    @Override
    fun compare(o1: List<T>, o2: List<T>): Int {
        val i1 = o1.iterator()
        val i2 = o2.iterator()
        var result: Int
        do {
            if (!i1.hasNext()) {
                return if (!i2.hasNext()) 0 else -1
            }
            if (!i2.hasNext()) {
                return 1
            }
            result = i1.next()!!.compareTo(i2.next())
        } while (result == 0)
        return result
    }
}