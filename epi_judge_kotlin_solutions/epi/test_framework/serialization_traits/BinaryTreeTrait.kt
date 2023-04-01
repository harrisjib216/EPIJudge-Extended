package epi.test_framework.serialization_traits

import epi.BinaryTree

class BinaryTreeTrait(nodeType: Class<*>, innerTypeTrait: SerializationTrait) : SerializationTrait() {
    private val nodeType: Class<*>
    private val innerTypeTrait: SerializationTrait

    init {
        this.nodeType = nodeType
        this.innerTypeTrait = innerTypeTrait
    }

    @Override
    override fun name(): String {
        return String.format("binary_tree(%s)", innerTypeTrait.name())
    }

    @Override
    override fun parse(jsonObject: JsonValue): Object {
        return if (nodeType.equals(BinaryTreeNode::class.java)) {
            buildBinaryTreeNode(jsonObject.asArray())
        } else if (nodeType.equals(BinaryTree::class.java)) {
            buildBinaryTree(jsonObject.asArray())
        } else if (nodeType.equals(BstNode::class.java)) {
            buildBstNode(jsonObject.asArray())
        } else {
            throw RuntimeException(
                    String.format("Binary Tree parser: %s class is not supported",
                            nodeType.toString()))
        }
    }

    @Override
    override fun getMetricNames(argName: String?): List<String> {
        return Arrays.asList(String.format("size(%s)", argName),
                String.format("height(%s)", argName))
    }

    @Override
    @SuppressWarnings("unchecked")
    override fun getMetrics(x: Object?): List<Integer> {
        return List.of(BinaryTreeUtils.binaryTreeSize(x as TreeLike<Object?, *>?),
                BinaryTreeUtils.binaryTreeHeight(x as TreeLike<Object?, *>?))
    }

    private fun buildBinaryTreeNode(data: JsonArray): Object {
        val nodes: List<BinaryTreeNode<Object>> = LinkedList()
        for (node in data) {
            nodes.add(if (node.isNull()) null else BinaryTreeNode(innerTypeTrait.parse(node)))
        }
        val candidateChildren: Deque<BinaryTreeNode<Object>> = LinkedList(nodes)
        val root: BinaryTreeNode<Object> = candidateChildren.removeFirst()
        for (node in nodes) {
            if (node != null) {
                if (!candidateChildren.isEmpty()) {
                    node.left = candidateChildren.removeFirst()
                }
                if (!candidateChildren.isEmpty()) {
                    node.right = candidateChildren.removeFirst()
                }
            }
        }
        return root
    }

    private fun buildBinaryTree(data: JsonArray): Object {
        val nodes: List<BinaryTree<Object>> = LinkedList()
        for (node in data) {
            nodes.add(if (node.isNull()) null else BinaryTree(innerTypeTrait.parse(node)))
        }
        val candidateChildren: Deque<BinaryTree<Object>> = LinkedList(nodes)
        val root: BinaryTree<Object> = candidateChildren.removeFirst()
        for (node in nodes) {
            if (node != null) {
                if (!candidateChildren.isEmpty()) {
                    node.left = candidateChildren.removeFirst()
                    if (node.left != null) {
                        node.left.parent = node
                    }
                }
                if (!candidateChildren.isEmpty()) {
                    node.right = candidateChildren.removeFirst()
                    if (node.right != null) {
                        node.right.parent = node
                    }
                }
            }
        }
        return root
    }

    private fun buildBstNode(data: JsonArray): Object {
        val nodes: List<BstNode<Object>> = LinkedList()
        for (node in data) {
            nodes.add(if (node.isNull()) null else BstNode(innerTypeTrait.parse(node)))
        }
        val candidateChildren: Deque<BstNode<Object>> = LinkedList(nodes)
        val root: BstNode<Object> = candidateChildren.removeFirst()
        for (node in nodes) {
            if (node != null) {
                if (!candidateChildren.isEmpty()) {
                    node.left = candidateChildren.removeFirst()
                }
                if (!candidateChildren.isEmpty()) {
                    node.right = candidateChildren.removeFirst()
                }
            }
        }
        return root
    }
}