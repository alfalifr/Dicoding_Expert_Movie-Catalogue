package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show

interface ShowFavRepo {
    fun getPopularMovieList(): Flow<List<Show>>
    fun getPopularTvList(): Flow<List<Show>>
    fun isShowFav(type: Int, id: Int): Flow<Boolean>
    suspend fun insertFav(show: Show, type: Int): Boolean
    suspend fun deleteFav(show: Show, type: Int): Int
    fun getFavMovie(id: Int): Flow<Show?>
    fun getFavTv(id: Int): Flow<Show?>
}