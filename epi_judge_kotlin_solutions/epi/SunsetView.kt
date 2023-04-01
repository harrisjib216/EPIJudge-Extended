package epi

import epi.test_framework.EpiTest

object SunsetView {
    fun examineBuildingsWithSunset(sequence: Iterator<Integer?>): List<Integer> {
        var buildingIdx = 0
        val candidates: Deque<BuildingWithHeight> = ArrayDeque()
        while (sequence.hasNext()) {
            val buildingHeight: Integer? = sequence.next()
            while (!candidates.isEmpty() && Integer.compare(buildingHeight, candidates.peekFirst().height) >= 0) {
                candidates.removeFirst()
            }
            candidates.addFirst(
                    BuildingWithHeight(buildingIdx++, buildingHeight))
        }
        return candidates.stream().map { c -> c.id }.collect(Collectors.toList())
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    fun examineBuildingsWithSunsetWrapper(sequence: List<Integer?>): List<Integer> {
        return examineBuildingsWithSunset(sequence.iterator())
    }

    fun main(args: Array<String?>?) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SunsetView.java",
                                object : Object() {}.getClass().getEnclosingClass())
                        .ordinal())
    }

    private class BuildingWithHeight(id: Integer, height: Integer?) {
        var id: Integer
        var height: Integer?

        init {
            this.id = id
            this.height = height
        }
    }
}