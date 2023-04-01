package epi

import epi.test_framework.EpiTest

object FindSalaryThreshold {
    @EpiTest(testDataFile = "find_salary_threshold.tsv")
    fun findSalaryCap(targetPayroll: Int,
                      currentSalaries: List<Integer?>?): Double {
        // TODO - you fill in here.
        return 0.0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "FindSalaryThreshold.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}