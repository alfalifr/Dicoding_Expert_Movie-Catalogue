package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.GetFavShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.AsyncVm

class ShowFavListViewModel(
    app: Application?,
    private val useCase: GetFavShowListUseCase,
): AsyncVm(app) {
    private val mFavList = MutableLiveData<List<Show>>()

    fun getFavList(type: Const.ShowType): LiveData<List<Show>> {
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