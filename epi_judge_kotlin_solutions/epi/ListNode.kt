package epi

import java.util.ArrayList

class ListNode<T>(var data: T, var next: ListNode<T>?) {
    fun toArray(): List<T> {
        val result: List<T> = ArrayList()
        var iter: ListNode<T>? = this
        while (iter != null) {
            result.add(iter.data)
            iter = iter.next
        }
        return result
    }

    @Override
    override fun toString(): String {
        val result = StringBuilder()
        val visited: Set<ListNode<T?>> = HashSet<ListNode<T>>()
        var node: ListNode<T?>? = this
        var first = true
        while (node != null) {
            if (first) {
                first = false
            } else {
                result.append(" -> ")
            }
            node = if (visited.contains(node)) {
                if (node.next !== node) {
                    result.append(if (node.data == null) "null" else node.data.toString())
                            .append(" -> ... -> ")
                }
                result.append(if (node.data == null) "null" else node.data.toString())
                        .append(" -> ...")
                break
            } else {
                result.append(if (node.data == null) "null" else node.data.toString())
                visited.add(node)
                node.next
            }
        }
        return result.toString()
    }

    @Override
    override fun equals(o: Object?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || getClass() !== o.getClass()) {
            return false
        }
        val that = o as ListNode<*>
        return equalsIterativeImpl(this, that)
    }

    // TODO: Factor out this function like C++ and Python by taking a parameter.
    fun size(): Int {
        var result = 0
        val visited: Set<ListNode<T>> = HashSet<ListNode<T>>()
        var node: ListNode<T>? = this
        while (node != null && !visited.contains(node)) {
            result++
            visited.add(node)
            node = node.next
        }
        return result
    }

    companion object {
        fun equalsIterativeImpl(a: ListNode<*>?, b: ListNode<*>?): Boolean {
            var a = a
            var b = b
            val visitedA: Set<ListNode<*>> = HashSet<ListNode<*>>()
            val visitedB: Set<ListNode<*>> = HashSet<ListNode<*>>()
            while (a != null && b != null) {
                if (visitedA.contains(a)) {
                    return a.data!!.equals(b.data) && visitedB.contains(b)
                }
                if (!a.data!!.equals(b.data)) {
                    return false
                }
                visitedA.add(a)
                visitedB.add(b)
                a = a.next
                b = b.next
            }
            return a == null && b == null
        }
    }
}