package sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.DummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.randomSubList
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain.usecase.SearchShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain.usecase.SearchShowUseCaseImpl

class SearchShowUseCaseTest {
    companion object {
        private val repo: ShowRepo by lazy { mock(ShowRepo::class.java) }
        private val useCase: SearchShowUseCase by lazy { SearchShowUseCaseImpl(repo) }

        private val randomMovieList = DummyData.movieDomains.randomSubList()
        private val randomTvList = DummyData.tvDomains.randomSubList()

        @JvmStatic
        @BeforeClass
        fun init() {
            `when`(repo.searchMovie(anyString())).thenReturn(
                flow { emit(randomMovieList) }
            )
            `when`(repo.searchTv(anyString())).thenReturn(
                flow { emit(randomTvList) }
            )
        }
    }

    @Test
    fun serachMovie(): Unit = runBlocking {
        useCase(Const.ShowType.MOVIE, "ay").collect {
            verify(repo).searchMovie("ay")
            assertEquals(randomMovieList, it)
        }
    }

    @Test
    fun serachTv(): Unit = runBlocking {
        useCase(Const.ShowType.TV, "oy").collect {
            verify(repo).searchTv("oy")
            assertEquals(randomTvList, it)
        }
    }
}