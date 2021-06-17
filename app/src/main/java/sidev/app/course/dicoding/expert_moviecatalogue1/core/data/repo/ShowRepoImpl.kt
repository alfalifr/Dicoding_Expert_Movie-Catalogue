package sidev.app.course.dicoding.expert_moviecatalogue1.core.data.repo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.api.ShowApi
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.DataMapper.toDetailModel
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.DataMapper.toModel
import javax.inject.Inject

class ShowRepoImpl @Inject constructor(private val api: ShowApi): ShowRepo {
    override fun getPopularMovieList(millisRefresh: Long?): Flow<List<Show>> = flow {
        if(millisRefresh == null) {
            val data = api.getPopularMovieList().toModel()
            emit(data)
        } else {
            while(true) {
                val data = api.getPopularMovieList().toModel()
                emit(data)
                delay(millisRefresh)
            }
        }
    }

    override fun getPopularTvList(millisRefresh: Long?): Flow<List<Show>> = flow {
        if(millisRefresh == null) {
            val data = api.getPopularTvList().toModel()
            emit(data)
        } else {
            while(true) {
                val data = api.getPopularTvList().toModel()
                emit(data)
                delay(millisRefresh)
            }
        }
    }

    override fun getMovieDetail(id: Int): Flow<ShowDetail> = flow {
        val data = api.getMovieDetail(id).toDetailModel()
        emit(data)
    }

    override fun getTvDetail(id: Int): Flow<ShowDetail> = flow {
        val data = api.getTvDetail(id).toDetailModel()
        emit(data)
    }

    override fun searchTv(keyword: String): Flow<List<Show>> = flow {
        val data = api.searchTv(keyword).toModel()
        emit(data)
    }

    override fun searchMovie(keyword: String): Flow<List<Show>> = flow {
        val data = api.searchMovie(keyword).toModel()
        emit(data)
    }
}