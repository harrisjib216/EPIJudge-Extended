package epi.test_framework

import java.util.List

class TestOutput(timer: TestTimer?, metrics: List<Integer?>?) {
    var timer: TestTimer?
    var metrics: List<Integer?>?

    init {
        this.timer = timer
        this.metrics = metrics
    }
}