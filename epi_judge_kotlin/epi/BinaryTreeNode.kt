package epi

import kotlin.Throws

class BinaryTreeNode<T> : TreeLike<T, BinaryTreeNode<T>?> {
    @get:Override
    override var data: T? = null

    @get:Override
    override var left: BinaryTreeNode<T>? = null

    @get:Override
    override var right: BinaryTreeNode<T>? = null

    constructor() {}
    constructor(data: T) {
        this.data = data
    }

    constructor(data: T, left: BinaryTreeNode<T>?,
                right: BinaryTreeNode<T>?) {
        this.data = data
        this.left = left
        this.right = right
    }
}