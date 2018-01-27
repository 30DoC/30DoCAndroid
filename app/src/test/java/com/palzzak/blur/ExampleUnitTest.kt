package com.palzzak.blur

import kotlinx.coroutines.experimental.*
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test fun corountine_test() {
        println("start")

        // do things on default coroutine(shared pool of threads)
        launch {
            // delay doesn't stop the thread, but only suspends the coroutine itself (non-blocking)
            // when coroutine is being suspended, the thread is returned to the pool till it's needed again
            // and when coroutine is done waiting, it finds a free thread in the pool and resumes
            delay(1000)
            println("hello")
        }

        // when we want to do delay on main thread
        runBlocking {
            delay(2000)
        }
        println("stop")
    }

    @Test fun is_coroutine_cheaper() {
        val c = AtomicInteger()
        for (i in 1..1_000_000) {
            launch {
                c.addAndGet(i)
            }
        }
        // not all of coroutines are completed, c is not what we expect
        println(c.get())
    }

    @Test fun async_test() {
        val deferred: List<Deferred<Int>> = (1..1_000_000).map { n ->
            async {
                workload(n)
            }
        }

        runBlocking {
            // await has to be called in coroutine
            val sum = deferred.sumBy { it.await() }
            println("sum : $sum")
        }
    }

    suspend fun workload(n: Int): Int {
        delay(1000)
        return n
    }
}
