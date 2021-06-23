package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetPopularShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetPopularShowListUseCaseImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.DummyData

class GetPopularShowListTest {
    companion object {
        private val repo: ShowRepo by lazy { mock(ShowRepo::class.java) }
        private val useCase: GetPopularShowListUseCase by lazy { GetPopularShowListUseCaseImpl(repo) }

        @JvmStatic
        @BeforeClass
        fun init(): Unit = runBlocking {
            `when`(repo.getPopularMovieList(null)).thenReturn(
                flow { emit(DummyData.movieDomains)  }
            )
            `when`(repo.getPopularTvList(null)).thenReturn(
                flow { emit(DummyData.tvDomains)  }
            )
        }
    }

    @Test
    fun getPopularTv(): Unit = runBlocking {
        useCase(Const.ShowType.TV).collect {
            verify(repo).getPopularTvList(null)
            assertEquals(DummyData.tvDomains, it)
        }
    }

    @Test
    fun getPopularMovie(): Unit = runBlocking {
        useCase(Const.ShowType.MOVIE).collect {
            verify(repo).getPopularMovieList(null)
            assertEquals(DummyData.movieDomains, it)
        }
    }
}