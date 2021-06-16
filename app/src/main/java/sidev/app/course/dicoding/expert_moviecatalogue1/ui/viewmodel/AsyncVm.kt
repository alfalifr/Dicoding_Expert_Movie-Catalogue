package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.jetbrains.anko.runOnUiThread
import kotlin.coroutines.CoroutineContext

/**
 * Template for all ViewModel in this project.
 * This class mimics [AndroidViewModel] but with optional [app] parameter for convinience in unit testing.
 */
@SuppressLint("StaticFieldLeak")
open class AsyncVm(app: Application?): ViewModel() {
    private var ctx: Context? = app
        private set

    private var mJobMap: MutableMap<String, Job>?= null

    /**
     * Executed before any async task in `this` runs.
     */
    protected var onPreAsyncTask: ((process: String) -> Unit)?= null
    private var onCallNotSuccess: ((process: String, code: Int, e: Throwable?) -> Unit)?= null

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    @CallSuper
    override fun onCleared() {
        ctx = null //So there won't be a memory leak.
        mJobMap = null
    }

    protected fun cancelJob(process: String){
        mJobMap?.get(process)?.cancel()
    }
    fun onPreAsyncTask(f: ((process: String) -> Unit)?){
        onPreAsyncTask= f
    }
    fun onCallNotSuccess(f: ((process: String, code: Int, e: Throwable?) -> Unit)?){
        onCallNotSuccess= f
    }
    protected fun doOnPreAsyncTask(process: String){
        onPreAsyncTask?.also { ctx?.runOnUiThread { it(process) } }
    }
    protected fun doCallNotSuccess(process: String, code: Int, e: Throwable?){
        onCallNotSuccess?.also { ctx?.runOnUiThread { it(process, code, e) } }
    }

    fun doJob(
        process: String,
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        cancelJob(process)
        doOnPreAsyncTask(process)
        return viewModelScope.launch(context, start, block).also { job ->
            (mJobMap ?: mutableMapOf<String, Job>().also { map -> mJobMap = map })[process] = job
        }
    }
}