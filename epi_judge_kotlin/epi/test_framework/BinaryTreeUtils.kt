package epi.test_framework

import epi.TreeLike

object BinaryTreeUtils {
    fun <T> generatePreorder(tree: TreeLike<T, *>?): List<T> {
        val result: List<T> = ArrayList()
        val s: Stack<TreeLike<T, *>> = Stack()
        s.push(tree)
        while (!s.empty()) {
            val node: TreeLike<T, *> = s.pop() ?: continue
            result.add(node.getData())
            s.push(node.getRight())
            s.push(node.getLeft())
        }
        return result
    }

    fun <T> generateInorder(tree: TreeLike<T, *>?): List<T> {
        val result: List<T> = ArrayList()
        val s: Stack<TreeLike<T, *>> = Stack()
        s.push(tree)
        var initial = true
        if (tree == null) {
            return result
        }
        while (!s.empty()) {
            var node: TreeLike<T, *> = s.pop()
            if (initial) {
                initial = false
            } else {
                result.add(node.getData())
                node = node.getRight()
            }
            while (node != null) {
                s.push(node)
                node = node.getLeft()
            }
        }
        return result
    }

    fun <T> generatePostorder(tree: TreeLike<T, *>?): List<T> {
        val result: List<T> = ArrayList()
        val s: Stack<TreeLike<T, *>> = Stack()
        s.push(tree)
        while (!s.empty()) {
            val node: TreeLike<T, *> = s.pop() ?: continue
            result.add(node.getData())
            s.push(node.getLeft())
            s.push(node.getLeft())
        }
        Collections.reverse(result)
        return result
    }

    private fun <T, Node : TreeLike<T, Node>?> findNode(node: Node,
                                                        `val`: T): Node? {
        var node: Node? = node
        val s: Stack<Node> = Stack()
        s.push(node)
        while (!s.empty()) {
            node = s.pop()
            if (node == null) {
                continue
            }
            if (`val`!!.equals(node.getData())) {
                return node
            }
            s.push(node.getLeft())
            s.push(node.getRight())
        }
        return null
    }

    fun <T, Node : TreeLike<T, Node>?> mustFindNode(tree: Node,
                                                    `val`: T): Node {
        return findNode<T, Node>(tree, `val`)
                ?: throw RuntimeException(String.valueOf(`val`) +
                        " was not found in the tree")
    }

    fun <T> equalBinaryTrees(tree1: TreeLike<T, *>,
                             tree2: TreeLike<T, *>): Boolean {
        val s: Stack<TwoNodes<T>> = Stack()
        s.push(TwoNodes<T>(tree1, tree2))
        while (!s.empty()) {
            val nodes: TwoNodes<T> = s.pop()
            if (nodes.node1 == null != (nodes.node2 == null)) {
                return false
            }
            if (nodes.node1 != null) {
                if (!Objects.equals(nodes.node1.getData(), nodes.node2.getData())) {
                    return false
                }
                s.push(TwoNodes<T>(nodes.node1.getLeft(), nodes.node2.getLeft()))
                s.push(TwoNodes<T>(nodes.node1.getRight(), nodes.node2.getRight()))
            }
        }
        return true
    }

    @Throws(TestFailure::class)
    fun <T> assertEqualBinaryTrees(expected: TreeLike<T, *>,
                                   result: TreeLike<T, *>) {
        val s: Stack<TwoNodesAndPath<T>> = Stack()
        s.push(TwoNodesAndPath<T>(expected, result, TreePath()))
        while (!s.empty()) {
            val nodes: TwoNodesAndPath<T> = s.pop()
            val expectedData: T? = if (nodes.node1 != null) nodes.node1.getData() else null
            val resultData: T? = if (nodes.node2 != null) nodes.node2.getData() else null
            if (!Objects.equals(expectedData, resultData)) {
                throw TestFailure()
                        .withProperty(TestFailure.PropertyName.RESULT, result)
                        .withProperty(TestFailure.PropertyName.EXPECTED, expected)
                        .withMismatchInfo(nodes.path, expectedData, resultData)
            }
            if (nodes.node1 != null && nodes.node2 != null) {
                s.push(TwoNodesAndPath<T>(nodes.node1.getLeft(),
                        nodes.node2.getLeft(),
                        nodes.path.withLeft()))
                s.push(TwoNodesAndPath<T>(nodes.node1.getRight(),
                        nodes.node2.getRight(),
                        nodes.path.withRight()))
            }
        }
    }

    @Throws(TestFailure::class)
    fun assertTreeIsBst(tree: TreeLike<Integer, *>) {
        val s: Stack<TreePathIntRange<Integer>> = Stack()
        s.push(TreePathIntRange<T>(tree, TreePath(), IntRange()))
        while (!s.empty()) {
            val node: TreePathIntRange<Integer> = s.pop()
            if (node.tree == null) {
                continue
            }
            val value: Integer = node.tree.getData()
            if (!node.range.contains(value)) {
                throw TestFailure("Binary search tree constraints violation")
                        .withProperty(TestFailure.PropertyName.RESULT, tree)
                        .withMismatchInfo(node.path, "Value in " + node.range.toString(),
                                value)
            }
            s.push(TreePathIntRange<T>(node.tree.getLeft(), node.path.withLeft(),
                    node.range.limitFromTop(value)))
            s.push(TreePathIntRange<T>(node.tree.getRight(), node.path.withRight(),
                    node.range.limitFromBottom(value)))
        }
    }

    fun <T> binaryTreeToString(tree: TreeLike<T, *>?): String {
        val result = StringBuilder()
        val nodes: Queue<TreeLike<T, *>> = LinkedList()
        val visited: Set<TreeLike<T, *>?> = Collections.newSetFromMap(IdentityHashMap())
        var first = true
        var nullNodesPending = 0
        result.append("[")
        nodes.add(tree)
        while (!nodes.isEmpty()) {
            val node: TreeLike<T, *> = nodes.poll()
            if (visited.contains(node)) {
                throw RuntimeException("Detected a cycle in the tree")
            }
            if (node != null) {
                if (first) {
                    first = false
                } else {
                    result.append(", ")
                }
                while (nullNodesPending > 0) {
                    result.append("null, ")
                    nullNodesPending--
                }
                result.append('"').append(node.getData()).append('"')
                visited.add(node)
                nodes.add(node.getLeft())
                nodes.add(node.getRight())
            } else {
                nullNodesPending++
            }
        }
        result.append("]")
        return result.toString()
    }

    fun <T> binaryTreeHeight(tree: TreeLike<T, *>): Int {
        val s: Stack<TreeInteger<T>> = Stack()
        s.push(TreeInteger<T>(tree, 1))
        var height = 0
        while (!s.empty()) {
            val node: TreeInteger<T> = s.pop()
            if (node.tree == null) {
                continue
            }
            height = Integer.max(height, node.height)
            s.push(TreeInteger<T>(node.tree.getLeft(), node.height + 1))
            s.push(TreeInteger<T>(node.tree.getRight(), node.height + 1))
        }
        return height
    }

    fun <T> binaryTreeSize(tree: TreeLike<T, *>?): Int {
        val s: Stack<TreeLike<T, *>> = Stack()
        s.push(tree)
        var size = 0
        while (!s.empty()) {
            val node: TreeLike<T, *> = s.pop() ?: continue
            size++
            s.push(node.getLeft())
            s.push(node.getRight())
        }
        return size
    }

    fun <T> binaryTreeHash(tree: TreeLike<T, *>?): Int {
        val EMPTY_NODE_HASH = 1000003 // Some random prime
        var result = 0
        val s: Stack<TreeLike<T, *>> = Stack()
        s.push(tree)
        while (!s.empty()) {
            val node: TreeLike<T, *> = s.pop()
            if (node == null) {
                result = Objects.hash(result, EMPTY_NODE_HASH)
                continue
            }
            result = Objects.hash(result, node.getData())
            s.push(node.getRight())
            s.push(node.getLeft())
        }
        return result
    }

    class TreePath {
        private var prev: TreePath? = null
        private var toLeft = false

        constructor() {}
        private constructor(prev: TreePath, toLeft: Boolean) {
            this.prev = prev
            this.toLeft = toLeft
        }

        fun withLeft(): TreePath {
            return TreePath(this, true)
        }

        fun withRight(): TreePath {
            return TreePath(this, false)
        }

        @Override
        override fun toString(): String {
            val result: List<String> = ArrayList()
            var node: TreePath? = this
            while (node != null) {
                result.add(if (node.toLeft) "->left" else "->right")
                node = node.prev
            }
            Collections.reverse(result)
            result.set(0, "root")
            return String.join("", result)
        }
    }

    private class IntRange @JvmOverloads constructor(private val low: Int = Integer.MIN_VALUE, private val high: Int = Integer.MAX_VALUE) {
        operator fun contains(value: Int): Boolean {
            return low <= value && value <= high
        }

        fun limitFromBottom(newLow: Int): IntRange {
            return if (newLow > low) {
                IntRange(newLow, high)
            } else {
                this
            }
        }

        fun limitFromTop(newHigh: Int): IntRange {
            return if (newHigh < high) {
                IntRange(low, newHigh)
            } else {
                this
            }
        }

        @Override
        override fun toString(): String {
            return String.format("range between %d and %d", low, high)
        }
    }

    private class TwoNodes<T>(node1: TreeLike<T, *>, node2: TreeLike<T, *>) {
        val node1: TreeLike<T, *>
        val node2: TreeLike<T, *>

        init {
            this.node1 = node1
            this.node2 = node2
        }
    }

    private class TwoNodesAndPath<T>(tree1: TreeLike<T, *>, tree2: TreeLike<T, *>,
                                     val path: TreePath) : TwoNodes<T>(tree1, tree2)

    private class TreePathIntRange<T>(tree: TreeLike<T, *>, path: TreePath,
                                      range: IntRange) {
        val tree: TreeLike<T, *>
        val path: TreePath
        val range: IntRange

        init {
            this.tree = tree
            this.path = path
            this.range = range
        }
    }

    private class TreeInteger<T>(tree: TreeLike<T, *>, height: Integer) {
        val tree: TreeLike<T, *>
        val height: Integer

        init {
            this.tree = tree
            this.height = height
        }
    }
}