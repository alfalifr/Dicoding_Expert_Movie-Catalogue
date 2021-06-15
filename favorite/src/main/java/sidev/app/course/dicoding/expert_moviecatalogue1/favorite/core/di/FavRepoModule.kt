package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di

import dagger.Binds
import dagger.Module
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.datasource.ShowFavRepoImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import javax.inject.Singleton

@Module
abstract class FavRepoModule {
    @Singleton
    @Binds
    abstract fun getShowFavRepo(impl: ShowFavRepoImpl): ShowFavRepo
}