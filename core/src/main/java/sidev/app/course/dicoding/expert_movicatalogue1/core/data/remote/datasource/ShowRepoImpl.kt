package sidev.app.course.dicoding.expert_movicatalogue1.core.data.remote.datasource

import android.content.Context
import sidev.app.course.dicoding.expert_movicatalogue1.core.data.local.room.ShowFavDao
import sidev.app.course.dicoding.expert_movicatalogue1.core.data.remote.api.ShowApi
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.Fail
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.Result
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.Success
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_movicatalogue1.core.util.Util

class ShowRepoImpl(private val api: ShowApi): ShowRepo {
    override suspend fun getPopularMovieList(): Result<List<Show>> {
        val res = api.getPopularMovieList().execute()
        if(!res.isSuccessful)
            return Util.canGetFail()

        
    }

    override suspend fun getPopularTvList(): Result<List<Show>> = Success()

    override suspend fun getMovieDetail(id: String): Result<ShowDetail> {

    }

    override suspend fun getTvDetail(id: String): Result<ShowDetail> {

    }

    override suspend fun searchTv(keyword: String): Result<List<Show>> {

    }

    override suspend fun searchMovie(keyword: String): Result<List<Show>> {

    }
}