package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.AsyncVm
import javax.inject.Inject

class ShowDetailFavViewModel @Inject constructor(
    c: Application?,
    private val showRepo: ShowRepo,
    private val favRepo: ShowFavRepo,
    private val type: Const.ShowType,
): AsyncVm(c) {
    private var currentShowDetailId: Int? = null
    private val mShowDetail: MutableLiveData<ShowDetail> = MutableLiveData()
    private val mIsFav: MutableLiveData<Boolean> = MutableLiveData()

    fun getShowDetail(id: Int): LiveData<ShowDetail> {
        if(currentShowDetailId != id || mShowDetail.value == null) {
            doJob(Const.GET_SHOW_DETAIL) {
                val result = when(type){
                    Const.ShowType.MOVIE -> showRepo.getMovieDetail(id)
                    Const.ShowType.TV -> showRepo.getTvDetail(id)
                }
                result.catch { doCallNotSuccess(Const.GET_SHOW_DETAIL, -1, it) }
                    .collect { mShowDetail.postValue(it) }
            }
            currentShowDetailId = id
        }
        return mShowDetail
    }

    fun isFav(showId: Int): LiveData<Boolean> {
        if(currentShowDetailId != showId || mIsFav.value == null) {
            doJob(Const.GET_IS_FAV) {
                favRepo.isShowFav(type.ordinal, showId).catch { doCallNotSuccess(Const.GET_IS_FAV, -1, it) }
                    .collect { mIsFav.postValue(it) }
            }
            currentShowDetailId = showId
        }
        return mIsFav
    }

    fun insertFav(show: Show){
        doJob(Const.INSERT_FAV) {
            val isFav = try {
                favRepo.insertFav(show, type.ordinal)
                true
            } catch (e: Throwable) { false }
            mIsFav.postValue(isFav)
        }
    }

    fun deleteFav(show: Show) {
        doJob(Const.DELETE_FAV) {
            val isNotFav = favRepo.deleteFav(show, type.ordinal) == 1
            mIsFav.postValue(!isNotFav)
        }
    }
}