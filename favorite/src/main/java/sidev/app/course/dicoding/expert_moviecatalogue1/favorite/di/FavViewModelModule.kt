package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.ui.FavViewModelFactory
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowDetailFavViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavListViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel

@Module
object FavViewModelModule {
    @LifecycleOwnerScope
    @Provides
    fun getFavViewModelProvider(
        owner: ViewModelStoreOwner,
        factory: FavViewModelFactory,
    ): ViewModelProvider = ViewModelProvider(owner, factory)

    @LifecycleOwnerScope
    @Provides
    fun getShowDetailViewModel(
        provider: ViewModelProvider
    ): ShowDetailViewModel = provider[ShowDetailViewModel::class.java]

    @LifecycleOwnerScope
    @Provides
    fun getShowDetailFavViewModel(
        provider: ViewModelProvider
    ): ShowDetailFavViewModel = provider[ShowDetailFavViewModel::class.java]

    @LifecycleOwnerScope
    @Provides
    fun getShowFavViewModel(
        provider: ViewModelProvider
    ): ShowFavListViewModel = provider[ShowFavListViewModel::class.java]
}