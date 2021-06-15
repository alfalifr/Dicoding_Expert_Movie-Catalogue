package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import java.io.Serializable


interface ShowRepo: Serializable {
    fun getPopularMovieList(millisRefresh: Long? = null): Flow<List<Show>>
    fun getPopularTvList(millisRefresh: Long? = null): Flow<List<Show>>
    fun getMovieDetail(id: Int): Flow<ShowDetail>
    fun getTvDetail(id: Int): Flow<ShowDetail>
    fun searchTv(keyword: String): Flow<List<Show>>
    fun searchMovie(keyword: String): Flow<List<Show>>
}