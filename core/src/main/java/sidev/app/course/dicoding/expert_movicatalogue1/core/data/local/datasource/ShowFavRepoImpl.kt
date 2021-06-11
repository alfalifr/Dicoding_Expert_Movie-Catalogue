package sidev.app.course.dicoding.expert_movicatalogue1.core.data.local.datasource

import sidev.app.course.dicoding.expert_movicatalogue1.core.data.local.room.ShowFavDao
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_movicatalogue1.core.util.DataMapper.entitiesToShowModelList
import sidev.app.course.dicoding.expert_movicatalogue1.core.util.DataMapper.toShowEntity
import sidev.app.course.dicoding.expert_movicatalogue1.core.util.DataMapper.toModel

class ShowFavRepoImpl(private val dao: ShowFavDao): ShowFavRepo {
    override fun getPopularMovieList(): List<Show> = dao.getFavMovies().entitiesToShowModelList()
    override fun getPopularTvList(): List<Show> = dao.getFavTv().entitiesToShowModelList()
    override fun isShowFav(type: Int, id: Int): Boolean = dao.isFav(type, id)
    override fun insertFav(show: Show, type: Int) = dao.insert(show.toShowEntity(type))
    override fun deleteFav(show: Show, type: Int) = dao.delete(show.toShowEntity(type))
    override fun getFavMovie(id: Int): Show? = dao.getFavMovieById(id)?.toModel()
    override fun getFavTv(id: Int): Show? = dao.getFavTvById(id)?.toModel()
}