package sidev.app.course.dicoding.expert_moviecatalogue1.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetPopularShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.SearchShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowListViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowSearchViewModel
import sidev.lib.`val`.SuppressLiteral

@Module
class ViewModelModule {
    @LifecycleOwnerScope
    @Provides
    fun getShowListViewModel(
        owner: ViewModelStoreOwner,
        app: Application,
        useCase: GetPopularShowListUseCase,
        type: Const.ShowType,
    ): ShowListViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowListViewModel(app, useCase, type) as T
        }
    ).get(ShowListViewModel::class.java)

    @LifecycleOwnerScope
    @Provides
    fun getShowDetailViewModel(
        owner: ViewModelStoreOwner,
        app: Application,
        useCase: GetShowDetailUseCase,
        type: Const.ShowType,
    ): ShowDetailViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowDetailViewModel(app, type, useCase) as T
        }
    ).get(ShowDetailViewModel::class.java)

    @LifecycleOwnerScope
    @Provides
    fun getSearchViewModel(
        owner: ViewModelStoreOwner,
        app: Application,
        useCase: SearchShowUseCase,
    ): ShowSearchViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowSearchViewModel(app, useCase) as T
        }
    ).get(ShowSearchViewModel::class.java)
}