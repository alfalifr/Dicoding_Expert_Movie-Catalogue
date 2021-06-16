package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room.ShowFavDao
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toShowEntity
import javax.inject.Inject

class ShowFavRepoImpl @Inject constructor(private val dao: ShowFavDao): ShowFavRepo {
    override fun getPopularMovieList(): Flow<List<Show>> = dao.getFavMovies()
        .map { it.map { item -> item.toModel() } }
        .flowOn(Dispatchers.IO)

    override fun getPopularTvList(): Flow<List<Show>> = dao.getFavTv()
        .map { it.map { item -> item.toModel() } }
        .flowOn(Dispatchers.IO)

    override fun isShowFav(type: Int, id: Int): Flow<Boolean> = dao.isFav(type, id)

    override suspend fun insertFav(show: Show, type: Int): Boolean {
        val entity = show.toShowEntity(type)
        dao.insert(entity)
        val res = dao.isFav(entity).firstOrNull()
        return res == true
    }

    override suspend fun deleteFav(show: Show, type: Int): Int = dao.delete(show.toShowEntity(type))

    override fun getFavMovie(id: Int): Flow<Show?> = dao.getFavMovieById(id).map { it?.toModel() }

    override fun getFavTv(id: Int): Flow<Show?> = dao.getFavTvById(id).map { it?.toModel() }
}