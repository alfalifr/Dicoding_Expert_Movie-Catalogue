package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.AsyncVm
import sidev.lib.android.std.tool.util.`fun`.loge
import javax.inject.Inject

class ShowFavListViewModel @Inject constructor(
    private val repo: ShowFavRepo,
    private val type: Const.ShowType,
): AsyncVm(null) {
/*
    companion object {
        fun getInstance(
            owner: ViewModelStoreOwner,
            repo: ShowFavRepo,
            type: Const.ShowType,
        ): ShowFavViewModel = ViewModelProvider(
            owner,
            object: ViewModelProvider.Factory {
                @Suppress(SuppressLiteral.UNCHECKED_CAST)
                override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowFavViewModel(repo, type) as T
            }
        ).get(ShowFavViewModel::class.java)
    }
 */

    private val mShowSrc = MediatorLiveData<PagingData<Show>>()

    fun getFavList(): LiveData<PagingData<Show>> {
        if(mShowSrc.value == null) {
            doJob(Const.GET_FAV_LIST) {

                val result = when(type){
                    Const.ShowType.TV -> repo.getPopularTvList()
                    Const.ShowType.MOVIE -> repo.getPopularMovieList()
                }
                result.catch { doCallNotSuccess(Const.GET_FAV_LIST, -1, it) }
                    .collect {

                        val pager = Pager(PagingConfig(8)) { it }
                    }

            }
        }
    }
    fun queryFavList(forceLoad: Boolean = false) {
        if(!forceLoad && mShowSrc.value != null) return
        cancelJob()
        loge("queryFavList() AppConfig.incUiAsync() onPreAsyncTask= $onPreAsyncTask")
        doOnPreAsyncTask()
        job = GlobalScope.launch(Dispatchers.IO) {
            val pager = Pager(PagingConfig(8)) {
                when(type){
                    Const.ShowType.TV -> repo.getPopularTvList()
                    Const.ShowType.MOVIE -> repo.getPopularMovieList()
                }
            }
            withContext(Dispatchers.Main) {
                mShowSrc.addSource(pager.liveData) { mShowSrc.postValue(it) }
            }
        }
    }
}