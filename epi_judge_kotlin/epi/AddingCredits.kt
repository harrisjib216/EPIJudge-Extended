package epi

import epi.test_framework.EpiTest

object AddingCredits {
    @EpiTest(testDataFile = "adding_credits.tsv")
    @Throws(TestFailure::class)
    fun ClientsCreditsInfoTester(ops: List<Operation>) {
        val cr = ClientsCreditsInfo()
        var opIdx = 0
        for (x in ops) {
            val sArg = x.sArg
            val iArg = x.iArg
            var result: Int
            when (x.op) {
                "ClientsCreditsInfo" -> {}
                "remove" -> {
                    result = if (cr.remove(sArg)) 1 else 0
                    if (result != iArg) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, cr)
                                .withProperty(TestFailure.PropertyName.COMMAND, x)
                                .withMismatchInfo(opIdx, iArg, result)
                    }
                }
                "insert" -> cr.insert(sArg, iArg)
                "add_all" -> cr.addAll(iArg)
                "lookup" -> {
                    result = cr.lookup(sArg)
                    if (result != iArg) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, cr)
                                .withProperty(TestFailure.PropertyName.COMMAND, x)
                                .withMismatchInfo(opIdx, iArg, result)
                    }
                }
            }
            opIdx++
        }
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AddingCredits.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    class ClientsCreditsInfo {
        fun insert(clientID: String?, c: Int) {
            // TODO - you fill in here.
            return
        }

        fun remove(clientID: String?): Boolean {
            // TODO - you fill in here.
            return true
        }

        fun lookup(clientID: String?): Int {
            // TODO - you fill in here.
            return 0
        }

        fun addAll(C: Int) {
            // TODO - you fill in here.
            return
        }

        fun max(): String {
            // TODO - you fill in here.
            return ""
        }

        @Override
        override fun toString(): String {
            // TODO - you fill in here.
            return super.toString()
        }
    }

    @EpiUserType(ctorParams = [String::class, String::class, Int::class])
    class Operation(var op: String, var sArg: String, var iArg: Int) {
        @Override
        override fun equals(o: Object?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || getClass() !== o.getClass()) {
                return false
            }
            val operation = o as Operation
            if (iArg != operation.iArg) {
                return false
            }
            return if (!op.equals(operation.op)) {
                false
            } else sArg.equals(operation.sArg)
        }

        @Override
        override fun hashCode(): Int {
            var result = op.hashCode()
            result = 31 * result + sArg.hashCode()
            result = 31 * result + iArg
            return result
        }

        @Override
        override fun toString(): String {
            return String.format("%s(%s, %d)", op, sArg, iArg)
        }
    }
}