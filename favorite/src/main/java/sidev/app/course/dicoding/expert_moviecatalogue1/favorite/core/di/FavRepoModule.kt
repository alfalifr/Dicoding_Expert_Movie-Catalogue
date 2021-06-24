@file:Suppress("unused", "unused", "unused", "unused")

package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di

import dagger.Binds
import dagger.Module
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.repo.ShowFavRepoImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo

@Module(includes = [DbModule::class])
abstract class FavRepoModule {
    @ModuleScope
    @Binds
    abstract fun getShowFavRepo(impl: ShowFavRepoImpl): ShowFavRepo
}