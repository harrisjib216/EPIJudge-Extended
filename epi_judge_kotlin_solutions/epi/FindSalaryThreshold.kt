package epi

import epi.test_framework.EpiTest

object FindSalaryThreshold {
    @EpiTest(testDataFile = "find_salary_threshold.tsv")
    fun findSalaryCap(targetPayroll: Int,
                      currentSalaries: List<Integer>): Double {
        Collections.sort(currentSalaries)
        var unadjustedSalarySum = 0.0
        for (i in 0 until currentSalaries.size()) {
            val adjustedPeople: Int = currentSalaries.size() - i
            val adjustedSalarySum: Double = currentSalaries[i] * adjustedPeople
            if (unadjustedSalarySum + adjustedSalarySum >= targetPayroll) {
                return (targetPayroll - unadjustedSalarySum) / adjustedPeople
            }
            unadjustedSalarySum += currentSalaries[i]
        }
        // No solution, since targetPayroll > existing payroll.
        return -1.0
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "FindSalaryThreshold.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }
}