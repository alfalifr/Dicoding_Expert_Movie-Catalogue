package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Inject

class ShowDetailViewModel @Inject constructor(
    c: Application?,
    private val showRepo: ShowRepo,
    private val type: Const.ShowType,
): AsyncVm(c) {
    private var currentShowDetailId: Int? = null
    private val mShowDetail: MutableLiveData<ShowDetail> = MutableLiveData()

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
}