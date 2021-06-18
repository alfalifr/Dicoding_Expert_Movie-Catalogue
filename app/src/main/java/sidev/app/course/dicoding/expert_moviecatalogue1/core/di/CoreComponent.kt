package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.di.SubComponentModule
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, UseCaseModule::class, SubComponentModule::class])
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CoreComponent
    }

    fun lifecycleOwnerSubComponent(): LifecycleOwnerComponent.Factory
    fun app(): Application
    fun showRepo(): ShowRepo
    fun getShowDetailUseCase(): GetShowDetailUseCase
}