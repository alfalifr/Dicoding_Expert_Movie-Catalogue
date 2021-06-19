package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.ui.AsyncVm
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

class ShowDetailViewModel(
    c: Application?,
    private val useCase: GetShowDetailUseCase,
): AsyncVm(c) {
    private var currentShowDetailId: Int? = null
    private val mShowDetail: MutableLiveData<ShowDetail> = MutableLiveData()

    fun getShowDetail(type: Const.ShowType, id: Int): LiveData<ShowDetail> {
        if(currentShowDetailId != id || mShowDetail.value == null) {
            doJob(Const.GET_SHOW_DETAIL) {
                val result = useCase(type, id)
                result.catch { doCallNotSuccess(Const.GET_SHOW_DETAIL, -1, it) }
                    .collect { mShowDetail.postValue(it) }
            }
            currentShowDetailId = id
        }
        return mShowDetail
    }
}