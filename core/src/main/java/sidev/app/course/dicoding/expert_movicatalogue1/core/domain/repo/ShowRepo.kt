package sidev.app.course.dicoding.expert_movicatalogue1.core.domain.repo

import android.content.Context
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.Result
import java.io.Serializable


interface ShowRepo: Serializable {
    suspend fun getPopularMovieList(): Result<List<Show>>
    suspend fun getPopularTvList(): Result<List<Show>>
    suspend fun getMovieDetail(id: String): Result<ShowDetail>
    suspend fun getTvDetail(id: String): Result<ShowDetail>
    suspend fun searchTv(keyword: String): Result<List<Show>>
    suspend fun searchMovie(keyword: String): Result<List<Show>>
}