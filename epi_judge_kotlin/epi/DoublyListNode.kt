package epi

import kotlin.Throws

class DoublyListNode<T> internal constructor(var data: T, var prev: DoublyListNode<T>?, var next: DoublyListNode<T>?)