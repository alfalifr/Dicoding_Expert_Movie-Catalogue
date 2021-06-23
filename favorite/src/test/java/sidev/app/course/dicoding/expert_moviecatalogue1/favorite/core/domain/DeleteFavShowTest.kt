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
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.DeleteFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.DeleteFavShowUseCaseImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel

class DeleteFavShowTest {
    companion object {
        private val repo: ShowFavRepo by lazy { mock(ShowFavRepo::class.java) }
        private val useCase: DeleteFavShowUseCase by lazy { DeleteFavShowUseCaseImpl(repo) }

        private val randomShowEntity = FavDummyData.favList.random()
        private val randomShow = randomShowEntity.toModel()

        @JvmStatic
        @BeforeClass
        fun init(): Unit = runBlocking {
            `when`(repo.deleteFav(randomShow, randomShowEntity.type)).thenReturn(1)
        }
    }

    @Test
    fun test(): Unit = runBlocking {
        val type = Const.ShowType.values()[randomShowEntity.type]
        useCase(type, randomShow).collect {
            verify(repo).deleteFav(randomShow, randomShowEntity.type)
            assertEquals(true, it)
        }
    }
}