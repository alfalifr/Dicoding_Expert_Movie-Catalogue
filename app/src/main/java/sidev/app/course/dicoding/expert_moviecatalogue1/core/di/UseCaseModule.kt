package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import dagger.Binds
import dagger.Module
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.*
import javax.inject.Singleton

@Module(includes = [RepoModule::class])
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun getPopularShowListUseCase(useCase: GetPopularShowListUseCaseImpl): GetPopularShowListUseCase
    @Singleton
    @Binds
    abstract fun getShowDetailUseCase(useCase: GetShowDetailUseCaseImpl): GetShowDetailUseCase
    @Singleton
    @Binds
    abstract fun searcShowUseCase(useCase: SearchShowUseCaseImpl): SearchShowUseCase
}