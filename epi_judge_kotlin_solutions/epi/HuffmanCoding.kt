package epi

import epi.test_framework.EpiTest

object HuffmanCoding {
    @EpiTest(testDataFile = "huffman_coding.tsv")
    fun huffmanEncoding(symbols: List<CharWithFrequency>): Double {
        val candidates: PriorityQueue<BinaryTree> = PriorityQueue()
        // Add leaves for symbols.
        for (s in symbols) {
            candidates.add(BinaryTree(s.freq, s, null, null))
        }

        // Keeps combining two nodes until there is one node left, which is the
        // root.
        while (candidates.size() > 1) {
            val left: BinaryTree = candidates.remove()
            val right: BinaryTree = candidates.remove()
            candidates.add(BinaryTree(left.aggregateFreq + right.aggregateFreq,
                    null, left, right))
        }
        val charToEncoding: Map<Character, String> = HashMap()
        // Traverses the binary tree, assigning codes to nodes.
        assignHuffmanCode(candidates.peek(), StringBuilder(), charToEncoding)
        return symbols.stream()
                .mapToDouble { s -> charToEncoding[s.c]!!.length() * s.freq / 100.0 }
                .sum()
    }

    private fun assignHuffmanCode(tree: BinaryTree?, code: StringBuilder,
                                  charToEncoding: Map<Character, String>) {
        if (tree != null) {
            if (tree.s != null) {
                // This node is a leaf.
                charToEncoding.put(tree.s!!.c, code.toString())
            } else { // Non-leaf node.
                code.append('0')
                assignHuffmanCode(tree.left, code, charToEncoding)
                code.setLength(code.length() - 1)
                code.append('1')
                assignHuffmanCode(tree.right, code, charToEncoding)
                code.setLength(code.length() - 1)
            }
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "HuffmanCoding.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    @EpiUserType(ctorParams = [String::class, Double::class])
    class CharWithFrequency(s: String, freq: Double) {
        var c: Char
        var freq: Double
        var code: String? = null

        init {
            if (s.length() !== 1) {
                throw RuntimeException(
                        "CharWithFrequency parser: string length is not 1")
            }
            c = s.charAt(0)
            this.freq = freq
        }
    }

    class BinaryTree(var aggregateFreq: Double, var s: CharWithFrequency?,
                     var left: BinaryTree?, var right: BinaryTree?) : Comparable<BinaryTree?> {
        @Override
        operator fun compareTo(o: BinaryTree): Int {
            return Double.compare(aggregateFreq, o.aggregateFreq)
        }

        @Override
        override fun equals(obj: Object?): Boolean {
            if (obj == null || obj !is BinaryTree) {
                return false
            }
            return if (this === obj) true else aggregateFreq == (obj as BinaryTree).aggregateFreq
        }

        @Override
        override fun hashCode(): Int {
            return Objects.hash(aggregateFreq)
        }
    }
}