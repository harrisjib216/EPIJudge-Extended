package epi

import kotlin.Throws

class PostingListNode(var order: Int, var next: PostingListNode,
                      var jump: PostingListNode?) {
    @Override
    override fun toString(): String {
        return String.format("(%d)%s%s", order, if (next != null) "->" else "",
                if (next != null) next.toString() else "")
    }
}