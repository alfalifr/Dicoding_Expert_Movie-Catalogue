package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCaseImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.DummyData

class GetShowDetailTest {
    companion object {
        private val repo: ShowRepo by lazy { mock(ShowRepo::class.java) }
        private val useCase: GetShowDetailUseCase by lazy { GetShowDetailUseCaseImpl(repo) }

        private val randomMovieDetail = DummyData.movieDetailDomains.random()
        private val randomTvDetail = DummyData.tvDetailDomains.random()

        @JvmStatic
        @BeforeClass
        fun init(): Unit = runBlocking {
            `when`(repo.getMovieDetail(anyInt())).thenReturn(
                flow { emit(randomMovieDetail) }
            )
            `when`(repo.getTvDetail(anyInt())).thenReturn(
                flow { emit(randomTvDetail) }
            )
        }
    }

    @Test
    fun getMovieDetail(): Unit = runBlocking {
        useCase(Const.ShowType.MOVIE, 1).collect {
            verify(repo).getMovieDetail(1)
            assertEquals(randomMovieDetail, it)
        }
    }

    @Test
    fun getTvDetail(): Unit = runBlocking {
        useCase(Const.ShowType.TV, 4).collect {
            verify(repo).getTvDetail(4)
            assertEquals(randomTvDetail, it)
        }
    }
}