package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import dagger.Binds
import dagger.Module
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.datasource.ShowRemoteSource
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class RepoModule {
    @Singleton
    @Binds
    abstract fun getShowRepo(impl: ShowRemoteSource): ShowRepo
}