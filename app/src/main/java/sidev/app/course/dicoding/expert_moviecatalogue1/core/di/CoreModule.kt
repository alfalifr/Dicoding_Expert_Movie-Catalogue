package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class CoreModule {
    @Provides
    fun getAppContext(app: Application): Context = app
}