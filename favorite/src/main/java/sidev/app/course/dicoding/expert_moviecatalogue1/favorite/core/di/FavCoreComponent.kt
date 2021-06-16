package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di

import dagger.Component
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.CoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.RepoModule
import sidev.app.course.dicoding.expert_moviecatalogue1.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.di.FavLifecycleOwnerComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.di.FavSubComponentModule

@ModuleScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [DbModule::class, FavRepoModule::class, FavSubComponentModule::class],
)
interface FavCoreComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavCoreComponent
    }

    fun favLifecycleOwnerSubComponent(): FavLifecycleOwnerComponent.Factory
}