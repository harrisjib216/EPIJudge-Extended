package epi

import epi.test_framework.BinaryTreeUtils

abstract class TreeLike<T, Node : TreeLike<T, Node>?> {
    abstract val data: T
    abstract val left: Node
    abstract val right: Node

    @Override
    @SuppressWarnings("unchecked")
    override fun equals(o: Object): Boolean {
        if (this === o) {
            return true
        }
        return if (o is TreeLike<*, *>) {
            BinaryTreeUtils.equalBinaryTrees(this as TreeLike<Object?, *>,
                    o as TreeLike<Object?, *>)
        } else false
    }

    @Override
    override fun hashCode(): Int {
        return BinaryTreeUtils.binaryTreeHash(this)
    }

    @Override
    override fun toString(): String {
        return BinaryTreeUtils.binaryTreeToString(this)
    }
}