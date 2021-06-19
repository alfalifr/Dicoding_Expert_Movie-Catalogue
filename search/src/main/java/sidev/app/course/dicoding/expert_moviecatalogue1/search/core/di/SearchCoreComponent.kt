package sidev.app.course.dicoding.expert_moviecatalogue1.search.core.di

import dagger.Component
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.CoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.search.di.SearchLifecycleOwnerComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.search.di.SearchSubComponentModule

@ModuleScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [SearchUseCaseModule::class, SearchSubComponentModule::class]
)
interface SearchCoreComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): SearchCoreComponent
    }

    fun searchLifecycleOwnerComponent(): SearchLifecycleOwnerComponent.Factory
}