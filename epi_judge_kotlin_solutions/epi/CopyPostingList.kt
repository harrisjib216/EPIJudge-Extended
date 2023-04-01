package epi

import epi.test_framework.EpiTest

object CopyPostingList {
    fun copyPostingsList(l: PostingListNode?): PostingListNode? {
        if (l == null) {
            return null
        }

        // Stage 1: Makes a copy of the original list without assigning the jump
        //          field, and creates the mapping for each node in the original
        //          list to the copied list.
        var iter: PostingListNode? = l
        while (iter != null) {
            val newNode = PostingListNode(iter.order, iter.next, null)
            iter.next = newNode
            iter = newNode.next
        }

        // Stage 2: Assigns the jump field in the copied list.
        iter = l
        while (iter != null) {
            if (iter.jump != null) {
                iter.next.jump = iter.jump.next
            }
            iter = iter.next.next
        }

        // Stage 3: Reverts the original list, and assigns the next field of
        //          the copied list.
        iter = l
        val newListHead: PostingListNode = iter.next
        while (iter!!.next != null) {
            val temp: PostingListNode = iter!!.next
            iter!!.next = temp.next
            iter = temp
        }
        return newListHead
    }

    fun createPostingList(serialized: List<SerializedNode>): PostingListNode? {
        val keyMapping: Map<Integer, PostingListNode> = HashMap()
        var head: PostingListNode? = null
        for (i in serialized.size() - 1 downTo 0) {
            head = PostingListNode(serialized[i].order, head!!, null)
            keyMapping.put(head.order, head)
        }
        val listIt: PostingListNode? = head
        for (x in serialized) {
            if (x.jumpIndex != -1) {
                listIt!!.jump = keyMapping[x.jumpIndex]
                if (listIt!!.jump == null) {
                    throw RuntimeException("Jump index out of range")
                }
            }
        }
        return head
    }

    @Throws(TestFailure::class)
    fun assertListsEqual(orig: PostingListNode?,
                         copy: PostingListNode?) {
        val nodeMapping: Map<PostingListNode?, PostingListNode?> = HashMap()
        var oIt: PostingListNode? = orig
        var cIt: PostingListNode? = copy
        var idx = 0
        while (oIt != null) {
            if (cIt == null) {
                throw TestFailure("Copied list has fewer nodes than the original")
                        .withProperty(TestFailure.PropertyName.EXPECTED, orig)
                        .withProperty(TestFailure.PropertyName.RESULT, copy)
                        .withProperty(TestFailure.PropertyName.MISSING_ITEMS, oIt)
            }
            if (oIt.order !== cIt.order) {
                throw TestFailure()
                        .withProperty(TestFailure.PropertyName.EXPECTED, orig)
                        .withProperty(TestFailure.PropertyName.RESULT, copy)
                        .withMismatchInfo(idx, oIt.order, cIt.order)
            }
            nodeMapping.put(oIt, cIt)
            oIt = oIt.next
            cIt = cIt.next
            idx++
        }
        if (cIt != null) {
            throw TestFailure("Copied list has fewer nodes than the original")
                    .withProperty(TestFailure.PropertyName.EXPECTED, orig)
                    .withProperty(TestFailure.PropertyName.RESULT, copy)
                    .withProperty(TestFailure.PropertyName.EXCESS_ITEMS, cIt)
        }
        oIt = orig
        cIt = copy
        idx = 0
        while (oIt != null) {
            if (nodeMapping[cIt] != null) {
                throw TestFailure(
                        "Copied list contains a node from the original list")
                        .withProperty(TestFailure.PropertyName.EXPECTED, orig)
                        .withProperty(TestFailure.PropertyName.RESULT, copy)
                        .withProperty(TestFailure.PropertyName.MISMATCH_INDEX, idx)
                        .withProperty(TestFailure.PropertyName.RESULT_ITEM,
                                PostingListNode(cIt!!.order, null, null))
            }
            if (oIt.jump != null) {
                if (nodeMapping[oIt.jump] !== cIt!!.jump) {
                    throw TestFailure(
                            "Jump link points to a different nodes in the copied list")
                            .withProperty(TestFailure.PropertyName.EXPECTED, orig)
                            .withProperty(TestFailure.PropertyName.RESULT, copy)
                            .withMismatchInfo(idx, oIt, cIt)
                }
            } else {
                if (cIt!!.jump != null) {
                    throw TestFailure(
                            "Jump link points to a different nodes in the copied list")
                            .withProperty(TestFailure.PropertyName.EXPECTED, orig)
                            .withProperty(TestFailure.PropertyName.RESULT, copy)
                            .withMismatchInfo(idx, oIt, cIt)
                }
            }
            oIt = oIt.next
            cIt = cIt!!.next
            idx++
        }
    }

    @EpiTest(testDataFile = "copy_posting_list.tsv")
    @Throws(Exception::class)
    fun copyPostingsListWrapper(executor: TimedExecutor,
                                l: List<SerializedNode>) {
        val head: PostingListNode? = createPostingList(l)
        val copy: PostingListNode = executor.run { copyPostingsList(head) }
        assertListsEqual(head, copy)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CopyPostingList.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [Int::class, Int::class])
    class SerializedNode(var order: Int, var jumpIndex: Int)
}