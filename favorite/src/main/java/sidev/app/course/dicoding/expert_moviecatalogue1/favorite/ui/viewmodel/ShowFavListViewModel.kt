package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.AsyncVm
import javax.inject.Inject

class ShowFavListViewModel @Inject constructor(
    private val repo: ShowFavRepo,
    private val type: Const.ShowType,
): AsyncVm(null) {
    private val mFavList = MutableLiveData<List<Show>>()

    fun getFavList(): LiveData<List<Show>> {
        if(mFavList.value == null) {
            doJob(Const.GET_FAV_LIST) {
                val result = when(type){
                    Const.ShowType.TV -> repo.getPopularTvList()
                    Const.ShowType.MOVIE -> repo.getPopularMovieList()
                }
                result.catch { doCallNotSuccess(Const.GET_FAV_LIST, -1, it) }
                    .collect { mFavList.postValue(it) }
            }
        }
        return mFavList
    }
}