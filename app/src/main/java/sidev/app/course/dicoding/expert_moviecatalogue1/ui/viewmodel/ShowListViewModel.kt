package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetPopularShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

class ShowListViewModel(
    c: Application?,
    private val useCase: GetPopularShowListUseCase,
): AsyncVm(c) {

    private val mShowList: MutableLiveData<List<Show>> = MutableLiveData()

    fun getShowPopularList(type: Const.ShowType): LiveData<List<Show>> {
        if(mShowList.value == null) {
            doJob(Const.GET_SHOW_POPULAR_LIST) {
                val result = useCase(type, Const.TIME_REFRESH)
                result.catch { doCallNotSuccess(Const.GET_SHOW_POPULAR_LIST, -1, it) }
                    .collect { mShowList.postValue(it) }
            }
        }
        return mShowList
    }
}