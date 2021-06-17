package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.GetFavShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.AsyncVm
import javax.inject.Inject

class ShowFavListViewModel @Inject constructor(
    private val type: Const.ShowType,
    private val useCase: GetFavShowListUseCase,
): AsyncVm(null) {
    private val mFavList = MutableLiveData<List<Show>>()

    fun getFavList(): LiveData<List<Show>> {
        if(mFavList.value == null) {
            doJob(Const.GET_FAV_LIST) {
                val result = useCase(type)
                result.catch { doCallNotSuccess(Const.GET_FAV_LIST, -1, it) }
                    .collect { mFavList.postValue(it) }
            }
        }
        return mFavList
    }
}