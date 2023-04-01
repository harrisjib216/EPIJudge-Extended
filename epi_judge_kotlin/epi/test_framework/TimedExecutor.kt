package epi.test_framework

import java.util.concurrent.Callable

class TimedExecutor(timeoutSeconds: Long) {
    private val timer: TestTimer
    private val timeoutSeconds: Long

    init {
        timer = TestTimer()
        this.timeoutSeconds = timeoutSeconds
    }

    /**
     * Invokes func with a specified timeoutSeconds.
     * If func takes more than timeoutSeconds seconds to run,
     * TimeoutException is thrown.
     * If timeoutSeconds == 0, it simply calls the function.
     *
     * @return whatever func returns
     */
    @Throws(Exception::class)
    fun <ReturnType> run(func: Callable<ReturnType>): ReturnType {
        return if (timeoutSeconds == 0L) {
            // Timeout is disabled.
            timedCall<ReturnType>(func)
        } else {
            try {
                val executor: ExecutorService = Executors.newSingleThreadExecutor()
                val future: Future<ReturnType> = executor.submit { timedCall(func) }

                // This does not cancel the already-scheduled task.
                executor.shutdown()
                future.get(timeoutSeconds, TimeUnit.SECONDS)
            } catch (e: java.util.concurrent.TimeoutException) {
                throw TimeoutException(timeoutSeconds)
            } catch (e: InterruptedException) {
                throw RuntimeException(e.getMessage())
            } catch (e: ExecutionException) {
                val cause: Throwable = e.getCause()
                if (cause is Exception) {
                    throw e.getCause() as Exception
                } else {
                    throw Exception(cause)
                }
            }
        }
    }

    @Throws(Exception::class)
    private fun <ReturnType> timedCall(func: Callable<ReturnType>): ReturnType {
        timer.start()
        val result: ReturnType = func.call()
        timer.stop()
        return result
    }

    /**
     * Invokes func with a specified timeoutSeconds.
     * If func takes more than timeoutSeconds seconds to run,
     * TimeoutException is thrown.
     * If timeoutSeconds == 0, it simply calls the function.
     */
    @Throws(Exception::class)
    fun run(func: Runnable) {
        run({
            func.run()
            null
        })
    }

    fun getTimer(): TestTimer {
        return timer
    }
}