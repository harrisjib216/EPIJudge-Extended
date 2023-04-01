package epi

import epi.test_framework.EpiTest

object PhoneNumberMnemonic {
    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    fun phoneMnemonic(phoneNumber: String): List<String> {
        val mnemonics: List<String> = ArrayList()
        phoneMnemonicHelper(phoneNumber, 0, CharArray(phoneNumber.length()),
                mnemonics)
        return mnemonics
    }

    // The mapping from digit to corresponding characters.
    private val MAPPING = arrayOf("0", "1", "ABC", "DEF", "GHI",
            "JKL", "MNO", "PQRS", "TUV", "WXYZ")

    private fun phoneMnemonicHelper(phoneNumber: String, digit: Int,
                                    partialMnemonic: CharArray,
                                    mnemonics: List<String>) {
        if (digit == phoneNumber.length()) {
            // All digits are processed, so add partialMnemonic to mnemonics.
            // (We add a copy since subsequent calls modify partialMnemonic.)
            mnemonics.add(String(partialMnemonic))
        } else {
            // Try all possible characters for this digit.
            for (i in 0 until MAPPING[phoneNumber.charAt(digit) - '0'].length()) {
                val c: Char = MAPPING[phoneNumber.charAt(digit) - '0'].charAt(i)
                partialMnemonic[digit] = c
                phoneMnemonicHelper(phoneNumber, digit + 1, partialMnemonic, mnemonics)
            }
        }
    }

    @EpiTestComparator
    fun comp(expected: List<String?>, result: List<String?>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        return expected.equals(result)
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}