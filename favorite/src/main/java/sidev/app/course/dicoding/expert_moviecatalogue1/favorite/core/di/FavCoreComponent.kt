package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.RepoModule
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DbModule::class, RepoModule::class, FavRepoModule::class]
)
interface FavCoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): FavCoreComponent
    }

    fun getShowRepo(): ShowRepo
    fun getShowFavRepo(): ShowFavRepo
}