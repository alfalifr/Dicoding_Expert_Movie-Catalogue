package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.randomSubList
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.FavDummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.GetFavShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.GetFavShowListUseCaseImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel

class GetFavShowListTest {
    companion object {
        private val repo: ShowFavRepo by lazy { mock(ShowFavRepo::class.java) }
        private val useCase: GetFavShowListUseCase by lazy { GetFavShowListUseCaseImpl(repo) }

        private val randomFavList = FavDummyData.favList.randomSubList().map { it.toModel() }

        @JvmStatic
        @BeforeClass
        fun init() {
            `when`(repo.getFavShowList(anyInt())).thenReturn(
                flow { emit(randomFavList) }
            )
        }
    }

    @Test
    fun test(): Unit = runBlocking {
        val type = Const.ShowType.values().random()
        useCase(type).collect {
            verify(repo).getFavShowList(type.ordinal)
            assertEquals(randomFavList, it)
        }
    }
}