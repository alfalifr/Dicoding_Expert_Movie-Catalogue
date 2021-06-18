package sidev.app.course.dicoding.expert_moviecatalogue1.search.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.ui.SearchViewModelFactory
import sidev.app.course.dicoding.expert_moviecatalogue1.search.ui.viewmodel.ShowSearchViewModel

@Module
object SearchViewModelModule {
    @LifecycleOwnerScope
    @Provides
    fun getViewModelProvider(
        owner: ViewModelStoreOwner,
        factory: SearchViewModelFactory,
    ): ViewModelProvider = ViewModelProvider(owner, factory)

    @LifecycleOwnerScope
    @Provides
    fun getShowSearchViewModel(
        provider: ViewModelProvider
    ): ShowSearchViewModel = provider[ShowSearchViewModel::class.java]
}