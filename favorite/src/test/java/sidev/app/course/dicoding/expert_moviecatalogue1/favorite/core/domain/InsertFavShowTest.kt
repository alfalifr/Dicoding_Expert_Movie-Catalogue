package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.FavDummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.InsertFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.InsertFavShowUseCaseImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel

class InsertFavShowTest {
    companion object {
        private val repo: ShowFavRepo by lazy { mock(ShowFavRepo::class.java) }
        private val useCase: InsertFavShowUseCase by lazy { InsertFavShowUseCaseImpl(repo) }

        private val randomFavEntity = FavDummyData.favList.random()
        private val randomFav = randomFavEntity.toModel()

        @JvmStatic
        @BeforeClass
        fun init(): Unit = runBlocking {
            `when`(repo.insertFav(randomFav, randomFavEntity.type)).thenReturn(true)
        }
    }

    @Test
    fun test(): Unit = runBlocking {
        val type = Const.ShowType.values()[randomFavEntity.type]
        useCase(type, randomFav).collect {
            verify(repo).insertFav(randomFav, randomFavEntity.type)
            assertEquals(true, it)
        }
    }
}