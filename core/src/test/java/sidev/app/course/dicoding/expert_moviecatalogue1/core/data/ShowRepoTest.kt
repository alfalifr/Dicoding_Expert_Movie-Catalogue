package sidev.app.course.dicoding.expert_moviecatalogue1.core.data

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.api.ShowApi
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.repo.ShowRepoImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.DataMapper.toDetailModel
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.DataMapper.toModel
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.DummyData

class ShowRepoTest {
    companion object {
        private val showApi: ShowApi by lazy { mock(ShowApi::class.java) }
        private val repo: ShowRepo by lazy { ShowRepoImpl(showApi) }

        private val randomMovieResponseDetail = DummyData.movieDetailResponses.random()
        private val randomTvResponseDetail = DummyData.tvDetailResponses.random()

        @JvmStatic
        @BeforeClass
        fun init(): Unit = runBlocking {
            `when`(showApi.getPopularMovieList(anyInt())).thenReturn(DummyData.movieListResponse)
            `when`(showApi.getPopularTvList(anyInt())).thenReturn(DummyData.tvListResponse)

            `when`(showApi.getMovieDetail(anyInt())).thenReturn(randomMovieResponseDetail)
            `when`(showApi.getTvDetail(anyInt())).thenReturn(randomTvResponseDetail)

            `when`(showApi.searchMovie(anyString(), anyInt())).thenReturn(DummyData.movieSearchListResponse)
            `when`(showApi.searchTv(anyString(), anyInt())).thenReturn(DummyData.tvSearchListResponse)
        }
    }

    @Test
    fun getPopularMovieList(): Unit = runBlocking {
        repo.getPopularMovieList().collect {
            verify(showApi).getPopularMovieList()
            assertEquals(DummyData.movieListResponse.toModel(), it)
        }
    }

    @Test
    fun getPopularTvList(): Unit = runBlocking {
        repo.getPopularTvList().collect {
            verify(showApi).getPopularTvList()
            assertEquals(DummyData.tvListResponse.toModel(), it)
        }
    }

    @Test
    fun getMovieDetail(): Unit = runBlocking {
        repo.getMovieDetail(1).collect {
            verify(showApi).getMovieDetail(1)
            assertEquals(randomMovieResponseDetail.toDetailModel(), it)
        }
    }

    @Test
    fun getTvDetail(): Unit = runBlocking {
        repo.getTvDetail(1).collect {
            verify(showApi).getTvDetail(1)
            assertEquals(randomTvResponseDetail.toDetailModel(), it)
        }
    }

    @Test
    fun searchTv(): Unit = runBlocking {
        repo.searchTv("ay").collect {
            verify(showApi).searchTv("ay")
            assertEquals(DummyData.tvSearchListResponse.toModel(), it)
        }
    }

    @Test
    fun searchMovie(): Unit = runBlocking {
        repo.searchMovie("oy").collect {
            verify(showApi).searchMovie("oy")
            assertEquals(DummyData.movieSearchListResponse.toModel(), it)
        }
    }
}