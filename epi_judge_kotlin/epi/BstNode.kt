package epi

import kotlin.Throws

class BstNode<T> : TreeLike<T, BstNode<T>?> {
    @get:Override
    override var data: T? = null

    @get:Override
    override var left: BstNode<T>? = null

    @get:Override
    override var right: BstNode<T>? = null

    constructor() {}
    constructor(data: T) {
        this.data = data
    }

    constructor(data: T, left: BstNode<T>?, right: BstNode<T>?) {
        this.data = data
        this.left = left
        this.right = right
    }
}