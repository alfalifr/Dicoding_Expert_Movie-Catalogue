package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetPopularShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.ui.AsyncVm
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

class ShowListViewModel(
    c: Application?,
    private val useCase: GetPopularShowListUseCase,
): AsyncVm(c) {

    private var currentShowType: Const.ShowType? = null
    private val mShowList: MutableLiveData<List<Show>> = MutableLiveData()

    fun getShowPopularList(type: Const.ShowType, refreshMillis: Long? = Const.TIME_REFRESH): LiveData<List<Show>> {
        if(mShowList.value == null || type != currentShowType) {
            doJob(Const.GET_SHOW_POPULAR_LIST) {
                val result = useCase(type, refreshMillis)
                result.catch { doCallNotSuccess(Const.GET_SHOW_POPULAR_LIST, -1, it) }
                    .collect { mShowList.postValue(it) }
                currentShowType = type
            }
        }
        return mShowList
    }

    override fun onCleared() {
        super.onCleared()
        currentShowType = null
    }
}