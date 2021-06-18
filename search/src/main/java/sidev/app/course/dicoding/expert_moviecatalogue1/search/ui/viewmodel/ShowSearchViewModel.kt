package sidev.app.course.dicoding.expert_moviecatalogue1.search.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain.usecase.SearchShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.AsyncVm

class ShowSearchViewModel(
    app: Application?,
    private val useCase: SearchShowUseCase,
): AsyncVm(app) {
    val searchList: LiveData<List<Show>> get()= mSearchList
    private val mSearchList = MutableLiveData<List<Show>>()

    var selectedTabPosition = 0
    private val validSearchRegex by lazy { "[a-zA-Z0-9]+".toRegex() }
    private var delay = 1500L

    private val mQueryState: MutableStateFlow<Pair<Const.ShowType, String>?> = MutableStateFlow(null)
    val searchValidState: Flow<Boolean> = mQueryState.map { query ->
        if(query != null) {
            val (type, keyword) = query
            validSearchRegex.containsMatchIn(keyword).let { isValid ->
                if(isValid) {
                    cancelJob(Const.SEARCH_SHOW)
                    delay(delay)
                    doJob(Const.SEARCH_SHOW) {
                        val result = useCase(type, keyword)
                        result.catch { doCallNotSuccess(Const.SEARCH_SHOW, -1, it) }
                            .collect { mSearchList.postValue(it) }
                    }
                }
                isValid || keyword.isEmpty()
            }
        } else true
    }.flowOn(Dispatchers.IO)

    fun searchShow(type: Const.ShowType, keyword: String? = null, delay: Long = 1500L) {
        this.delay = delay
        if(keyword != null) {
            mQueryState.value = type to keyword
        } else if(mQueryState.value != null) {
            mQueryState.value = type to mQueryState.value!!.second
        }
    }
}