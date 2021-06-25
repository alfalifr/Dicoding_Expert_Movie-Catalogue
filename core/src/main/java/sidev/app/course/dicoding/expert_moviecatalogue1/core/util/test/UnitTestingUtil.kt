package sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

object UnitTestingUtil {
    private const val DEFAULT_ASYNC_TIMEOUT = 10000L

    fun <T> LiveData<T>.waitForValue(
        timeout: Long = DEFAULT_ASYNC_TIMEOUT,
        timeUnit: TimeUnit = TimeUnit.MILLISECONDS,
        delay: Long = 0,
    ): T = runBlocking {

        delay(delay)

        if(value != null)
            return@runBlocking value!!

        val lock = CountDownLatch(1)
        var data: T? = null
        val observer = object: Observer<T> {
            /**
             * Called when the data is changed.
             * @param t  The new data
             */
            override fun onChanged(t: T) {
                data = t
                removeObserver(this)
                lock.countDown()
            }
        }
        observeForever(observer)

        @Suppress("BLOCKING_METHOD_IN_NON_BLOCKING_CONTEXT")
        if(!lock.await(timeout, timeUnit)){
            throw TimeoutException("The value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return@runBlocking data as T
    }

    fun <T> List<T>.randomSubList(): List<T> {
        val start = indices.random()
        var end = indices.random()
        if(end <= start) {
            end = start +1
        }
        return subList(start, end)
    }
}