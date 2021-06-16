package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.api.ShowApi
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Singleton

@Module
class NetworkModule {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
                    .build()
            )
            .baseUrl(Const.ENDPOINT_ROOT +"/")
            .build()
    }

    @Singleton
    @Provides
    fun showApi(): ShowApi = retrofit.create(ShowApi::class.java)
}