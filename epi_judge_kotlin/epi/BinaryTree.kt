package epi

import kotlin.Throws

class BinaryTree<T> : TreeLike<T, BinaryTree<T>?> {
    @get:Override
    override var data: T? = null

    @get:Override
    override var left: BinaryTree<T>? = null

    @get:Override
    override var right: BinaryTree<T>? = null
    var parent: BinaryTree<T>? = null

    constructor() {}
    constructor(data: T) {
        this.data = data
    }

    constructor(data: T, left: BinaryTree<T>?, right: BinaryTree<T>?) {
        this.data = data
        this.left = left
        this.right = right
    }

    constructor(data: T, left: BinaryTree<T>?, right: BinaryTree<T>?,
                parent: BinaryTree<T>?) : this(data, left, right) {
        this.parent = parent
    }
}