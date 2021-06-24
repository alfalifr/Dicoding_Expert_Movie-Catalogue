package sidev.app.course.dicoding.expert_moviecatalogue1.core.di

import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.api.ShowApi
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Singleton

@Module
object NetworkModule {
    private val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val interceptor = Interceptor { chain ->
            val oldRequest = chain.request()
            val oldUrl = oldRequest.url
            val newUrl = oldUrl.newBuilder()
                .addQueryParameter(Const.KEY_API_KEY, Const.API_KEY)
                .build()
            val newRequest = oldRequest.newBuilder()
                .url(newUrl)
                .build()
            loggingInterceptor.intercept(chain)
            chain.proceed(newRequest)
        }

        val certPinner = CertificatePinner.Builder()
            .add(Const.ENDPOINT_HOST, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
                //For Amazon provider
            .add(Const.ENDPOINT_HOST, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(Const.ENDPOINT_HOST, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .certificatePinner(certPinner)
            .build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(Const.ENDPOINT_ROOT +"/")
            .build()
    }

    @Singleton
    @Provides
    fun showApi(): ShowApi = retrofit.create(ShowApi::class.java)
}