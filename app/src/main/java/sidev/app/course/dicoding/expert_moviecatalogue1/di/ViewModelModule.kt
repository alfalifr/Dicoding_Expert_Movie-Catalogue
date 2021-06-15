package sidev.app.course.dicoding.expert_moviecatalogue1.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowListViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel
import sidev.lib.`val`.SuppressLiteral

@Module
object ViewModelModule {
    @Provides
    fun getShowListViewModel(
        owner: ViewModelStoreOwner,
        app: Application,
        repo: sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowRepo,
        type: Const.ShowType,
    ): ShowListViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowListViewModel(app, repo, type) as T
        }
    ).get(ShowListViewModel::class.java)

    @Provides
    fun getShowDetailViewModel(
        owner: ViewModelStoreOwner,
        app: Application,
        showRepo: sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowRepo,
        favRepo: sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo,
        type: Const.ShowType,
    ): ShowDetailViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowDetailViewModel(app, showRepo, favRepo, type) as T
        }
    ).get(ShowDetailViewModel::class.java)

    @Provides
    fun getShowFavViewModel(
        owner: ViewModelStoreOwner,
        repo: sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo,
        type: Const.ShowType,
    ): sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavViewModel(
                repo,
                type
            ) as T
        }
    ).get(sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavViewModel::class.java)
}