package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.DeleteFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.InsertFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.IsShowFavUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.AsyncVm
import sidev.lib.android.std.tool.util.`fun`.loge
import javax.inject.Inject

class ShowDetailFavViewModel @Inject constructor(
    c: Application?,
    private val type: Const.ShowType,
    private val isShowFavUseCase: IsShowFavUseCase,
    private val insertFavShowUseCase: InsertFavShowUseCase,
    private val deleteFavShowUseCase: DeleteFavShowUseCase,
): AsyncVm(c) {
    private var currentShowDetailId: Int? = null
    private val mIsFav: MutableLiveData<Boolean> = MutableLiveData()

    fun isFav(show: Show): LiveData<Boolean> {
        if(currentShowDetailId != show.id || mIsFav.value == null) {
            loge("isFav() id = $show")
            doJob(Const.GET_IS_FAV) {
                isShowFavUseCase(type, show)
                    .catch { doCallNotSuccess(Const.GET_IS_FAV, -1, it) }
                    .collect { mIsFav.postValue(it) }
            }
            currentShowDetailId = show.id
        }
        return mIsFav
    }

    fun insertFav(show: Show){
        doJob(Const.INSERT_FAV) {
            insertFavShowUseCase(type, show)
                .catch { doCallNotSuccess(Const.INSERT_FAV, -1, it) }
                .collect { mIsFav.postValue(it) }
        }
    }

    fun deleteFav(show: Show) {
        doJob(Const.DELETE_FAV) {
            deleteFavShowUseCase(type, show)
                .catch { doCallNotSuccess(Const.DELETE_FAV, -1, it) }
                .collect { mIsFav.postValue(!it) }
        }
    }
}