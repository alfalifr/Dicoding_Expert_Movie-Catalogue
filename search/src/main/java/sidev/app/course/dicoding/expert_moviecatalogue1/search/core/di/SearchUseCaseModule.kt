package sidev.app.course.dicoding.expert_moviecatalogue1.search.core.di

import dagger.Binds
import dagger.Module
import sidev.app.course.dicoding.expert_moviecatalogue1.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain.usecase.SearchShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain.usecase.SearchShowUseCaseImpl

@Module
abstract class SearchUseCaseModule {
    @ModuleScope
    @Binds
    abstract fun searcShowUseCase(useCase: SearchShowUseCaseImpl): SearchShowUseCase
}