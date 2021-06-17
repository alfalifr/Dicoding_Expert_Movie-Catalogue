package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room.ShowFavDao
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toShowEntity
import javax.inject.Inject

class ShowFavRepoImpl @Inject constructor(private val dao: ShowFavDao): ShowFavRepo {
    override fun getFavShowList(type: Int): Flow<List<Show>> = dao.getShows(type)
        .map { list -> list.map { it.toModel() } }
        .flowOn(Dispatchers.IO)

    override fun getFavShowById(type: Int, id: Int): Flow<Show?> = dao.getShowById(type, id)
        .map { it?.toModel() }
        .flowOn(Dispatchers.IO)

    override suspend fun insertFav(show: Show, type: Int): Boolean {
        val entity = show.toShowEntity(type)
        val rowId = dao.insert(entity)
        return rowId >= 0
    }

    override suspend fun deleteFav(show: Show, type: Int): Int = dao.delete(show.toShowEntity(type))
}