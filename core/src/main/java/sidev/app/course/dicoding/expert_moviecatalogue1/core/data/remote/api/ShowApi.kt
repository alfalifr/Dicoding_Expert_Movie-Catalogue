package sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowDetailResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowListResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

interface ShowApi {
    @GET(Const.PATH_MOVIE_POPULAR)
    suspend fun getPopularMovieList(
        @Query(Const.KEY_PAGE) page: Int = 1,
        @Query(Const.KEY_API_KEY) apiKey: String = Const.API_KEY,
    ): ShowListResponse

    @GET(Const.PATH_TV_POPULAR)
    suspend fun getPopularTvList(
        @Query(Const.KEY_PAGE) page: Int = 1,
        @Query(Const.KEY_API_KEY) apiKey: String = Const.API_KEY,
    ): ShowListResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query(Const.KEY_API_KEY) apiKey: String = Const.API_KEY,
    ): ShowDetailResponse

    @GET("tv/{id}")
    suspend fun getTvDetail(
        @Path("id") id: Int,
        @Query(Const.KEY_API_KEY) apiKey: String = Const.API_KEY,
    ): ShowDetailResponse

    @GET("search/tv")
    suspend fun searchTv(
        @Query(Const.KEY_KEYWORD) keyword: String,
        @Query(Const.KEY_PAGE) page: Int = 1,
        @Query(Const.KEY_API_KEY) apiKey: String = Const.API_KEY,
    ): ShowListResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query(Const.KEY_KEYWORD) keyword: String,
        @Query(Const.KEY_PAGE) page: Int = 1,
        @Query(Const.KEY_API_KEY) apiKey: String = Const.API_KEY,
    ): ShowListResponse
}