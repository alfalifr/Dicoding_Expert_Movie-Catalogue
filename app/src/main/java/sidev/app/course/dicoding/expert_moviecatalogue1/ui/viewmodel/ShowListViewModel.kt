package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Inject

class ShowListViewModel @Inject constructor(
    c: Application?,
    private val repo: ShowRepo,
    private val type: Const.ShowType,
): AsyncVm(c) {

    private val mShowList: MutableLiveData<List<Show>> = MutableLiveData()

    fun getShowPopularList(): LiveData<List<Show>> {
        if(mShowList.value == null) {
            doJob(Const.GET_SHOW_POPULAR_LIST) {
                val result = when(type){
                    Const.ShowType.MOVIE -> repo.getPopularMovieList()
                    Const.ShowType.TV -> repo.getPopularTvList()
                }
                result.catch { doCallNotSuccess(Const.GET_SHOW_POPULAR_LIST, -1, it) }
                    .collect { mShowList.postValue(it) }
            }
        }
        return mShowList
    }
}