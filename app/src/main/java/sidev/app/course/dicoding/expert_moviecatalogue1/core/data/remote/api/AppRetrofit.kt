package sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.api

import retrofit2.Retrofit
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

object AppRetrofit {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Const.ENDPOINT_ROOT +"/")
        .build()

    val showApi: ShowApi = retrofit.create(ShowApi::class.java)
}