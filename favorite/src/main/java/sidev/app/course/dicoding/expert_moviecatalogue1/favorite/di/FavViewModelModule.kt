package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
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
        favRepo: ShowFavRepo,
        type: Const.ShowType,
    ): ShowDetailViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowDetailFavViewModel(app, favRepo, type) as T
        }
    ).get(ShowDetailViewModel::class.java)

    @LifecycleOwnerScope
    @Provides
    fun getShowFavViewModel(
        owner: ViewModelStoreOwner,
        repo: ShowFavRepo,
        type: Const.ShowType,
    ): ShowFavListViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowFavListViewModel(
                repo,
                type
            ) as T
        }
    ).get(ShowFavListViewModel::class.java)
}