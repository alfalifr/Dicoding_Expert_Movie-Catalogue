package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show

interface ShowFavRepo {
    //fun getFavMovieList(): Flow<List<Show>>
    //fun getFavTvList(): Flow<List<Show>>
    //fun isShowFav(type: Int, id: Int): Flow<Boolean>
    fun getFavShowList(type: Int): Flow<List<Show>>
    fun getFavShowById(type: Int, id: Int): Flow<Show?>
    suspend fun insertFav(show: Show, type: Int): Boolean
    suspend fun deleteFav(show: Show, type: Int): Int
    //fun getFavMovie(id: Int): Flow<Show?>
    //fun getFavTv(id: Int): Flow<Show?>
}