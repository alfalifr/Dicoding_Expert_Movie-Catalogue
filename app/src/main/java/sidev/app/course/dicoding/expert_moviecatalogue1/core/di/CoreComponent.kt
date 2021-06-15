package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, NetworkModule::class, RepoModule::class])
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CoreComponent
    }

    fun getShowRepo(): ShowRepo
}