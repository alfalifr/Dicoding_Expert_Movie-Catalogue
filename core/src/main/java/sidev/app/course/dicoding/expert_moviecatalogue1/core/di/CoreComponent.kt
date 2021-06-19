package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetPopularShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCase
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, UseCaseModule::class])
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CoreComponent
    }

    fun app(): Application
    fun showRepo(): ShowRepo
    fun getShowDetailUseCase(): GetShowDetailUseCase
    fun getPopularShowListUseCase(): GetPopularShowListUseCase
}