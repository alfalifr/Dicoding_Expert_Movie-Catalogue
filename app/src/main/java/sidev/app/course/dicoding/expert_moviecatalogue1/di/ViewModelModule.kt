package sidev.app.course.dicoding.expert_moviecatalogue1.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowListViewModel
import sidev.lib.`val`.SuppressLiteral

@Module
class ViewModelModule {
    @LifecycleOwnerScope
    @Provides
    fun getShowListViewModel(
        owner: ViewModelStoreOwner,
        app: Application,
        repo: ShowRepo,
        type: Const.ShowType,
    ): ShowListViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowListViewModel(app, repo, type) as T
        }
    ).get(ShowListViewModel::class.java)

    @LifecycleOwnerScope
    @Provides
    fun getShowDetailViewModel(
        owner: ViewModelStoreOwner,
        app: Application,
        showRepo: ShowRepo,
        type: Const.ShowType,
    ): ShowDetailViewModel = ViewModelProvider(
        owner,
        object: ViewModelProvider.Factory {
            @Suppress(SuppressLiteral.UNCHECKED_CAST)
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowDetailViewModel(app, showRepo, type) as T
        }
    ).get(ShowDetailViewModel::class.java)
}