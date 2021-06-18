package sidev.app.course.dicoding.expert_moviecatalogue1.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.core.ui.ViewModelFactory
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowListViewModel

@Module
object ViewModelModule {
    @LifecycleOwnerScope
    @Provides
    fun getViewModelProvider(
        owner: ViewModelStoreOwner,
        factory: ViewModelFactory,
    ): ViewModelProvider = ViewModelProvider(owner, factory)

    @LifecycleOwnerScope
    @Provides
    fun getShowListViewModel(
        provider: ViewModelProvider
    ): ShowListViewModel = provider[ShowListViewModel::class.java]

    @LifecycleOwnerScope
    @Provides
    fun getShowDetailViewModel(
        provider: ViewModelProvider
    ): ShowDetailViewModel = provider[ShowDetailViewModel::class.java]
}