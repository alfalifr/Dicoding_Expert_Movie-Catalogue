package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di

import dagger.Binds
import dagger.Module
import sidev.app.course.dicoding.expert_moviecatalogue1.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.*

@Module(includes = [FavRepoModule::class])
abstract class FavUseCaseModule {
    @ModuleScope
    @Binds
    abstract fun getFavShowListUseCase(useCase: GetFavShowListUseCaseImpl): GetFavShowListUseCase
    @ModuleScope
    @Binds
    abstract fun insertFavShowUseCase(useCase: InsertFavShowUseCaseImpl): InsertFavShowUseCase
    @ModuleScope
    @Binds
    abstract fun deleteFavShowUseCase(useCase: DeleteFavShowUseCaseImpl): DeleteFavShowUseCase
    @ModuleScope
    @Binds
    abstract fun isShowFavUseCase(useCase: IsShowFavUseCaseImpl): IsShowFavUseCase
}