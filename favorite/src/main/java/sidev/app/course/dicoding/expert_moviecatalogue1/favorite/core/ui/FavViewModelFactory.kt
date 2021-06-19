package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.DeleteFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.GetFavShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.InsertFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.IsShowFavUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowDetailFavViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavListViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel
import sidev.lib.`val`.SuppressLiteral
import java.lang.IllegalArgumentException
import javax.inject.Inject

@ModuleScope
class FavViewModelFactory @Inject constructor(
    private val app: Application?,
    private val isShowFavUseCase: IsShowFavUseCase,
    private val insertFavShowUseCase: InsertFavShowUseCase,
    private val deleteFavShowUseCase: DeleteFavShowUseCase,
    private val getFavShowListUseCase: GetFavShowListUseCase,
    private val getShowDetailUseCase: GetShowDetailUseCase,
): ViewModelProvider.Factory {
    @Suppress(SuppressLiteral.UNCHECKED_CAST)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(ShowDetailViewModel::class.java) -> ShowDetailViewModel(
            app, getShowDetailUseCase
        )
        modelClass.isAssignableFrom(ShowDetailFavViewModel::class.java) -> ShowDetailFavViewModel(
            app, isShowFavUseCase, insertFavShowUseCase, deleteFavShowUseCase
        )
        modelClass.isAssignableFrom(ShowFavListViewModel::class.java) -> ShowFavListViewModel(
            app, getFavShowListUseCase
        )
        else -> throw IllegalArgumentException("No such ViewModel class ($modelClass) for FavViewModelFactory")
    } as T
}