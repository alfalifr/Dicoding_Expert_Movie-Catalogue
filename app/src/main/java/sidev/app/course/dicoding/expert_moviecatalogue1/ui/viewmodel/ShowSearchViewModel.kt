package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Inject

class ShowSearchViewModel @Inject constructor(
    app: Application?,
    private val repo: ShowRepo,
): AsyncVm(app) {
    val searchList: LiveData<List<Show>> get()= mSearchList
    private val mSearchList = MutableLiveData<List<Show>>()

    fun searchShow(keyword: String, type: Const.ShowType) {
        doJob(Const.SEARCH_SHOW) {
            val result = when(type) {
                Const.ShowType.MOVIE -> repo.searchMovie(keyword)
                Const.ShowType.TV -> repo.searchTv(keyword)
            }
            result.catch { doCallNotSuccess(Const.SEARCH_SHOW, -1, it) }
                .collect { mSearchList.postValue(it) }
        }
    }

}