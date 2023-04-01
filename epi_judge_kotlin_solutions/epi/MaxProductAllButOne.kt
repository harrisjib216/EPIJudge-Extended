package epi

import epi.test_framework.EpiTest

object MaxProductAllButOne {
    @EpiTest(testDataFile = "max_product_all_but_one.tsv")
    fun findBiggestProductNMinusOneProduct(A: List<Integer>): Int {

        // Builds suffix products.
        var product = 1
        val suffixProducts: List<Integer> = ArrayList(Collections.nCopies(A.size(), 0))
        for (i in A.size() - 1 downTo 0) {
            product *= A[i]
            suffixProducts.set(i, product)
        }

        // Finds the biggest product of (n - 1) numbers.
        var prefixProduct = 1
        var maxProduct: Int = Integer.MIN_VALUE
        for (i in 0 until A.size()) {
            val suffixProduct = if (i + 1 < A.size()) suffixProducts[i + 1] else 1
            maxProduct = Math.max(maxProduct, prefixProduct * suffixProduct)
            prefixProduct *= A[i]
        }
        return maxProduct
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxProductAllButOne.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}