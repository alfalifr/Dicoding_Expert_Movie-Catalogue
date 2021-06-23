package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.FavDummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.IsShowFavUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.IsShowFavUseCaseImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel

class IsShowFavTest {
    companion object {
        private val repo: ShowFavRepo by lazy { mock(ShowFavRepo::class.java) }
        private val useCase: IsShowFavUseCase by lazy { IsShowFavUseCaseImpl(repo) }

        private val randomFavEntity = FavDummyData.favList.random()
        private val randomFav = randomFavEntity.toModel()

        @JvmStatic
        @BeforeClass
        fun init() {
            `when`(repo.getFavShowById(randomFavEntity.type, randomFavEntity.id)).thenReturn(
                flow { emit(randomFav) }
            )
        }
    }

    @Test
    fun test(): Unit = runBlocking {
        val type = Const.ShowType.values()[randomFavEntity.type]
        useCase(type, randomFav).collect {
            verify(repo).getFavShowById(type.ordinal, randomFav.id)
            assertEquals(true, it)
        }
    }
}