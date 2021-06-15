package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.api.ShowApi
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Singleton

@Module
object NetworkModule {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Const.ENDPOINT_ROOT +"/")
            .build()
    }

    @Singleton
    @get:Provides
    val showApi: ShowApi
        get() = retrofit.create(ShowApi::class.java)
}