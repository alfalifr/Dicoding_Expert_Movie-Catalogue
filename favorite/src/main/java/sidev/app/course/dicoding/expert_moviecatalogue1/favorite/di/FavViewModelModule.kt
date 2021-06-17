package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.DeleteFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.GetFavShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.InsertFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.IsShowFavUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowDetailFavViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavListViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel
import sidev.lib.`val`.SuppressLiteral

@Module
class FavViewModelModule {
    @LifecycleOwnerScope
    @Provides
    fun getShowDetailViewModel(
        owner: ViewModelStoreOwner,
        app: Application,
        type: Const.ShowType,
        isShowFavUseCase: IsShowFavUseCase,
        insertFavShowUseCase: InsertFavShowUseCase,
        deleteFavShowUseCase: DeleteFavShowUseCase,
    ): ShowDetailViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowDetailFavViewModel(
                app, type, isShowFavUseCase, insertFavShowUseCase, deleteFavShowUseCase
            ) as T
        }
    ).get(ShowDetailViewModel::class.java)

    @LifecycleOwnerScope
    @Provides
    fun getShowFavViewModel(
        owner: ViewModelStoreOwner,
        type: Const.ShowType,
        useCase: GetFavShowListUseCase,
    ): ShowFavListViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowFavListViewModel(type,useCase) as T
        }
    ).get(ShowFavListViewModel::class.java)
}